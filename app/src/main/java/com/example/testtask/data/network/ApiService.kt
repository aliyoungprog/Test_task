package com.example.testtask.data.network

import com.example.testtask.domain.entity.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/search/movie")
    suspend fun getMovieByTitle(@Query("api_key") key: String, @Query("query") query: String) : Response<ApiResponse>

    @GET("3/movie/popular")
    suspend fun getPopular(@Query("api_key") key: String) : Response<ApiResponse>

    // should complete
    @GET("3/movie/popular")
    suspend fun getPopularByPage(@Query("api_key") key: String, page: Int) : Response<ApiResponse>

}