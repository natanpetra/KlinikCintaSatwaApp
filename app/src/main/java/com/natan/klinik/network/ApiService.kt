package com.natan.klinik.network

import com.natan.klinik.model.Clinic
import com.natan.klinik.model.DoctorItem
import com.natan.klinik.model.Guide
import com.natan.klinik.model.ProductItem
import com.natan.klinik.model.Profile
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("customer/sign-in") // Sesuaikan dengan endpoint API kamu
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Profile>

    @GET("product") // Sesuaikan dengan endpoint API kamu
    fun getProduct(
    ): Call<List<ProductItem>>

    @GET("clinic") // Sesuaikan dengan endpoint API kamu
    fun getClinic(
    ): Call <Clinic>

    @GET("doctors") // Sesuaikan dengan endpoint API kamu
    fun getDoctor(
    ): Call<List<DoctorItem>>

    @GET("dog-care-guides") // Sesuaikan dengan endpoint API kamu
    fun getGuide(
    ): Call<List<Guide>>
}
