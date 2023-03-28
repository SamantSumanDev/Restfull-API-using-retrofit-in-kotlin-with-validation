package com.example.myapplication.api

import com.example.myapplication.model.*
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("/api/unknown")
    suspend fun getlistunknown(): ListuserResponse

    @POST("/api/register")
    suspend fun postRegister(@Body params: PostRequest): Postapiresponse

    @GET("/api/users")
    suspend fun getUserList(): ListdataRes2

    companion object {
        fun create(): ApiService {
            val logger =
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            val client = OkHttpClient.Builder().addInterceptor(logger)
                .callTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://reqres.in")//RentainanceApp.getINSTANCE().getHostUrl
                .client(client)

                .build()
                .create(ApiService::class.java)
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        }
    }
}