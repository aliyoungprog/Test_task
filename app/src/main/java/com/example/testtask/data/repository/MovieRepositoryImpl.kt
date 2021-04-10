package com.example.testtask.data.repository

import com.example.testtask.data.network.ApiService
import com.example.testtask.data.network.ApiRetrofit

class MovieRepositoryImpl(val apiService: ApiService = ApiRetrofit.injectApiService()) {
    companion object{
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 10
        fun getInstance() = MovieRepositoryImpl()
    }
}