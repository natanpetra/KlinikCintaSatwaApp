package com.natan.klinik.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natan.klinik.R
import com.natan.klinik.adapter.MainAdapter
import com.natan.klinik.decoration.LayoutMarginDecoration
import com.natan.klinik.model.ModelMain
import com.natan.klinik.utils.Tools
import java.util.Calendar

class MainActivity : AppCompatActivity(), MainAdapter.OnSelectData {
    lateinit var rvMainMenu: RecyclerView
    var gridMargin: LayoutMarginDecoration? = null
    var mdlMainMenu: ModelMain? = null
    var lsMainMenu: MutableList<ModelMain?> = ArrayList<ModelMain?>()
    var tvToday: TextView? = null
    var hariIni: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        tvToday = findViewById(R.id.tvDate)
        rvMainMenu = findViewById(R.id.rvMainMenu)
        val mLayoutManager: GridLayoutManager = GridLayoutManager(
            this, 2,
            RecyclerView.VERTICAL, false
        )
        rvMainMenu.setLayoutManager(mLayoutManager)
        gridMargin = LayoutMarginDecoration(2, Tools.dp2px(this, 4f))
        rvMainMenu.addItemDecoration(gridMargin!!)
        rvMainMenu.setHasFixedSize(true)

        //get Time Now
        val dateNow = Calendar.getInstance().time
        hariIni = DateFormat.format("EEEE", dateNow) as String
        today
        setMenu()
    }

    private val today: Unit
        get() {
            val date = Calendar.getInstance().time
            val tanggal =
                DateFormat.format("d MMMM yyyy", date) as String
            val formatFix = "$hariIni, $tanggal"
            tvToday?.setText(formatFix)
        }

    private fun setMenu() {
        mdlMainMenu = ModelMain("Klinik", R.drawable.img_clinic)
        lsMainMenu.add(mdlMainMenu)
        mdlMainMenu = ModelMain("Dog Care", R.drawable.img_dog_guide)
        lsMainMenu.add(mdlMainMenu)
        mdlMainMenu = ModelMain("Dokter", R.drawable.img_doctor)
        lsMainMenu.add(mdlMainMenu)
        mdlMainMenu = ModelMain("Produk", R.drawable.img_produk)
        lsMainMenu.add(mdlMainMenu)

        val myAdapter: MainAdapter = MainAdapter(lsMainMenu, this)
        rvMainMenu.setAdapter(myAdapter)
    }

    fun call(phone: String) {
        val url = "https://api.whatsapp.com/send?phone=$phone"
        try {
            val pm: PackageManager = packageManager
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val i: Intent = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        } catch (e: PackageManager.NameNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    companion object {
        //set Transparent Status bar
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win: Window = activity.getWindow()
            val winParams: WindowManager.LayoutParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }

    override fun onSelected(mdlMain: ModelMain?) {
        TODO("Not yet implemented")
    }
}
