package com.example.testtask.data.network

import com.example.testtask.domain.entity.ApiResponse
import com.example.testtask.domain.entity.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("3/search/movie")
    suspend fun getMovies(@Query("api_key") key: String, @Query("query") query: String) : Response<ApiResponse>

    @GET("3/movie/popular")
    suspend fun getPopular(@Query("api_key") key: String) : Response<ApiResponse>

}