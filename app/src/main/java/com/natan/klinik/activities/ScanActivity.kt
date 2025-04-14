package com.natan.klinik.activities

import android.app.Activity
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.natan.klinik.R
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import kotlin.math.max
import kotlin.math.min

class ScanActivity : AppCompatActivity() {

    private lateinit var tflite: Interpreter
    private val labels = listOf("dermatitis", "flea_allergy", "ringworm", "scabies")
    private val inputSize = 640
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var imageView: ImageView
    private lateinit var selectButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        imageView = findViewById(R.id.imageView)
        selectButton = findViewById(R.id.selectButton)

        tflite = Interpreter(loadModelFile())

        selectButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = assets.openFd("best_float32_1.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val inputStream = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                bitmap?.let {
                    val resized = Bitmap.createScaledBitmap(it, inputSize, inputSize, true)
                    val resultBitmap = detectObjects(resized, it)
                    imageView.setImageBitmap(resultBitmap)
                }
            }
        }
    }

    private fun detectObjects(bitmap: Bitmap, originalBitmap: Bitmap): Bitmap {
        val input = Array(1) { Array(inputSize) { Array(inputSize) { FloatArray(3) } } }
        for (y in 0 until inputSize) {
            for (x in 0 until inputSize) {
                val px = bitmap.getPixel(x, y)
                input[0][y][x][0] = Color.red(px) / 255.0f
                input[0][y][x][1] = Color.green(px) / 255.0f
                input[0][y][x][2] = Color.blue(px) / 255.0f
            }
        }

        val output = Array(1) { Array(8) { FloatArray(8400) } }
        tflite.run(input, output)

        Log.d("RAW OUTPUT", output[0].contentDeepToString())

        val boxes = mutableListOf<RectF>()
        val scores = mutableListOf<Float>()
        val classIds = mutableListOf<Int>()

        for (i in 0 until 8400) {
            val score = output[0][4][i]
            if (score > 0.1f) {
                val numClasses = output[0].size - 5 // jumlah kelas = total channel - 5
                val clsScores = FloatArray(numClasses)
                for (j in 0 until numClasses) {
                    clsScores[j] = output[0][5 + j][i]
                }

                val clsMax = clsScores.maxOrNull() ?: 0f
                val clsId = clsScores.withIndex().maxByOrNull { it.value }?.index ?: -1

                if (clsId >= 0) {
                    val cx = output[0][0][i] * originalBitmap.width
                    val cy = output[0][1][i] * originalBitmap.height
                    val w = output[0][2][i] * originalBitmap.width
                    val h = output[0][3][i] * originalBitmap.height

                    val left = max(0f, cx - w / 2)
                    val top = max(0f, cy - h / 2)
                    val right = min(originalBitmap.width.toFloat(), cx + w / 2)
                    val bottom = min(originalBitmap.height.toFloat(), cy + h / 2)

                    boxes.add(RectF(left, top, right, bottom))
                    scores.add(clsMax)
                    classIds.add(clsId)
                }
            }
        }

        Log.wtf("Score", scores.toString())

        val selectedIndices = nms(boxes, scores, 0.1f)
        val mutableBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(mutableBitmap)

        val paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 4f
        }

        val textPaint = Paint().apply {
            color = Color.RED
            textSize = 40f
            typeface = Typeface.DEFAULT_BOLD
        }

        for (i in selectedIndices) {
            val box = boxes[i]
            val label = labels.getOrNull(classIds[i]) ?: "unknown"
            val scoreText = "${label}"
            canvas.drawRect(box, paint)
            canvas.drawText(scoreText, box.left, box.top - 10, textPaint)
        }

        return mutableBitmap
    }


    fun nms(
        boxes: List<RectF>,
        scores: List<Float>,
        iouThreshold: Float
    ): List<Int> {
        val indices = scores.indices.sortedByDescending { scores[it] }.toMutableList()
        val selected = mutableListOf<Int>()

        while (indices.isNotEmpty()) {
            val current = indices.removeAt(0)
            selected.add(current)

            val it = indices.iterator()
            while (it.hasNext()) {
                val idx = it.next()
                val iou = calculateIoU(boxes[current], boxes[idx])
                if (iou > iouThreshold) {
                    it.remove()
                }
            }
        }

        return selected
    }

    fun calculateIoU(box1: RectF, box2: RectF): Float {
        val x1 = maxOf(box1.left, box2.left)
        val y1 = maxOf(box1.top, box2.top)
        val x2 = minOf(box1.right, box2.right)
        val y2 = minOf(box1.bottom, box2.bottom)

        val interArea = maxOf(0f, x2 - x1) * maxOf(0f, y2 - y1)
        val box1Area = (box1.right - box1.left) * (box1.bottom - box1.top)
        val box2Area = (box2.right - box2.left) * (box2.bottom - box2.top)

        return interArea / (box1Area + box2Area - interArea)
    }
}
