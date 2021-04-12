package com.example.testtask.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.testtask.data.database.FavoriteMoviesDao
import com.example.testtask.data.network.ApiRetrofit
import com.example.testtask.data.network.ApiService
import com.example.testtask.domain.entity.FavoriteMoviesEntity
import com.example.testtask.domain.repository.MovieRepository
import com.example.testtask.presentation.utils.getDefaultPageConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieRepositoryImpl(private val dao: FavoriteMoviesDao) : MovieRepository {


    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }

    override suspend fun getPopularMovies(setMovies: (List<FavoriteMoviesEntity>) -> Unit){
        val api = ApiRetrofit.injectApiService()
        withContext(Dispatchers.IO){
            val movieList = api.getPopular("7c4afca12ec6c62155cbfa6647f584b7")
            setMovies(movieList.body()!!.items)
        }
    }

    override fun getPopularMoviesByPage(pagingConfig: PagingConfig) : LiveData<PagingData<FavoriteMoviesEntity>> {
        val apiService = ApiRetrofit.injectApiService()
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {MoviePagingSource(apiService)}
        ).liveData
    }


    override suspend fun addToFavorites(movie: FavoriteMoviesEntity) {
        movie.isFavorite = true
        dao.insertMovie(movie)
    }

    override suspend fun deleteFromFavorites(movie: FavoriteMoviesEntity) {
        dao.deleteMovie(movie)
    }

    override suspend fun getAllFavorites(setAllMovies: (List<FavoriteMoviesEntity>) -> Unit) {
        val list = dao.getFavoriteMovies()
        //Log.d("test", "onStart: ${dao.getFavoriteMovies().size}")
        setAllMovies(list)
    }

    override suspend fun searchByTitle(movieTitle: String, setMovies: (List<FavoriteMoviesEntity>) -> Unit) {
        val api = ApiRetrofit.injectApiService()
        withContext(Dispatchers.Main){
            val movieList = async { api.getMovieByTitle("7c4afca12ec6c62155cbfa6647f584b7", movieTitle) }
            if (movieList.await().body()?.items!!.isNotEmpty())
                setMovies(movieList.await().body()!!.items)
            else{
                setMovies(listOf())
            }
        }
    }

}
