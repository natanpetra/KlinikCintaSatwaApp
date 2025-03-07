package com.natan.klinik.network

import com.natan.klinik.model.Profile
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("customer/sign-in") // Sesuaikan dengan endpoint API kamu
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Profile>
}
