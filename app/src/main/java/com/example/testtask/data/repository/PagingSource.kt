package com.example.testtask.data.repository

import androidx.paging.PagingSource
import com.example.testtask.data.network.ApiService
import com.example.testtask.domain.entity.Movie

class PagingSource (val apiService: ApiService, private val query: String) : PagingSource<Int, Movie>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        TODO("Not yet implemented")
    }

}