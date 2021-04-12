package com.example.testtask.data.repository

import android.graphics.Movie
import androidx.paging.PagingSource
import com.example.testtask.data.network.ApiService
import com.example.testtask.domain.entity.FavoriteMoviesEntity

class PagingSource (val apiService: ApiService, private val query: String) : PagingSource<Int, FavoriteMoviesEntity>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteMoviesEntity> {
        TODO("Not yet implemented")
    }

}