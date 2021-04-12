package com.example.testtask.domain.di

import android.content.Context
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import com.example.testtask.data.database.FavoriteMovies
import com.example.testtask.data.database.FavoriteMoviesDao
import com.example.testtask.data.network.ApiService
import com.example.testtask.data.repository.MovieRepositoryImpl
import com.example.testtask.domain.repository.MovieRepository
import com.example.testtask.presentation.view_models.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val repositoryModule = module {

    single { createDataBase(get()) }
    single { createMovieDao(get()) }
    single { MovieRepositoryImpl(get()) as MovieRepository }
}

val viewModelModule = module {
    viewModel{
        MovieViewModel(get())
    }
}

fun createMovieDao(movieDatabase: FavoriteMovies): FavoriteMoviesDao {
    Log.d("cur context", "createDataBase: ${movieDatabase.getDao()}")
    return movieDatabase.getDao()
}

fun createDataBase(context: Context): FavoriteMovies {
    //Log.d("cur context", "createDataBase: $context")
    return FavoriteMovies.getDatabase(context = context)
}

val appModules = listOf(repositoryModule, viewModelModule)
