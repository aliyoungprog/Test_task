package com.example.testtask.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testtask.domain.entity.FavoriteMoviesEntity
import com.example.testtask.presentation.utils.getDefaultPageConfig

interface MovieRepository {
    suspend fun getPopularMovies(setMovies: (List<FavoriteMoviesEntity>) -> Unit)
    fun getPopularMoviesByPage(pagingConfig: PagingConfig = getDefaultPageConfig()) : LiveData<PagingData<FavoriteMoviesEntity>>
    suspend fun addToFavorites(movie: FavoriteMoviesEntity)
    suspend fun deleteFromFavorites(movie: FavoriteMoviesEntity)
    suspend fun getAllFavorites(setAllMovies: (List<FavoriteMoviesEntity>) -> Unit)
    suspend fun searchByTitle(movieTitle: String, setMovies: (List<FavoriteMoviesEntity>) -> Unit)
}

