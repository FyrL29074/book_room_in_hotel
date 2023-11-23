package com.fyrl29074.data

import com.fyrl29074.data.model.HotelDto
import com.fyrl29074.data.model.RoomsDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitService {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return okHttpClient
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    val api = retrofit.create(Api::class.java)
}

interface Api {

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): Response<HotelDto>

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): Response<RoomsDto>
}