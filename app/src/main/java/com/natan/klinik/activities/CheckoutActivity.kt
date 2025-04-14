package com.natan.klinik.activities

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.natan.klinik.R
import com.natan.klinik.databinding.ActivityBerandaBinding
import com.natan.klinik.databinding.ActivityCheckoutBinding
import com.natan.klinik.model.ItemKeranjang
import com.natan.klinik.utils.SessionManager

class CheckoutActivity : AppCompatActivity() {
    lateinit var binding : ActivityCheckoutBinding
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)
        val keranjang = session.getKeranjang()

        val listView = findViewById<ListView>(R.id.listKeranjang)
        val adapter = object : ArrayAdapter<ItemKeranjang>(this, android.R.layout.simple_list_item_2, android.R.id.text1, keranjang) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: layoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false)
                val item = getItem(position)!!
                view.findViewById<TextView>(android.R.id.text1).text = item.obat.name
                view.findViewById<TextView>(android.R.id.text2).text =
                    "Rp ${item.obat.price} x ${item.qty} = Rp ${(item.obat.price?.toInt()
                        ?.times(item.qty))}"
                return view
            }
        }
        listView.adapter = adapter

        val total = keranjang.sumOf { it.obat.price?.toInt()?.times(it.qty) ?: 0 }
        findViewById<TextView>(R.id.tvTotalHarga).text = "Total: Rp $total"

        findViewById<Button>(R.id.btnPesanSekarang).setOnClickListener {
            Toast.makeText(this, "Pesanan diproses!", Toast.LENGTH_SHORT).show()
            session.clearKeranjang()
            finish()
        }


    }
}