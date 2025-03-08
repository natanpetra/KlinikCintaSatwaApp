package com.natan.klinik.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natan.klinik.R
import com.natan.klinik.adapter.DoctorAdapter
import com.natan.klinik.adapter.ProductAdapter
import com.natan.klinik.model.DoctorItem
import com.natan.klinik.model.ProductItem
import com.natan.klinik.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListActivity : AppCompatActivity(), ProductAdapter.onSelectData {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private var productList: MutableList<ProductItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        recyclerView = findViewById(R.id.rvDoctor)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchProduct()
    }

    private fun fetchProduct() {
        RetrofitClient.instance.getProduct().enqueue(object : Callback<List<ProductItem>> {
            override fun onResponse(call: Call<List<ProductItem>>, response: Response<List<ProductItem>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        productList.addAll(data)
                        adapter = ProductAdapter(this@ProductListActivity, productList, this@ProductListActivity)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<ProductItem>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onSelected(modelProduct: ProductItem) {

    }
}