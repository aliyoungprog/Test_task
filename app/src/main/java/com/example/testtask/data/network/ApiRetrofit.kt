package com.example.testtask.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {

    private const val URL = "https://api.themoviedb.org"
    private const val API_KEY = "7c4afca12ec6c62155cbfa6647f584b7"

    fun injectApiService(retrofit: Retrofit = getRetrofit()): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    private fun getRetrofit(okHttpClient: OkHttpClient = getOkHttpClient()): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun getOkHttpClient(
        okHttpLogger: HttpLoggingInterceptor = getHttpInterceptor()
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpLogger)
            .build()
    }


    private fun getHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}