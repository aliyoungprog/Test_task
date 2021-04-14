package com.example.testtask.presentation.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.testtask.domain.entity.FavoriteMoviesEntity
import com.example.testtask.domain.repository.MovieRepository
import com.example.testtask.presentation.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel (private val repository: MovieRepository) : ParentViewModel() {

    val popularMoviesLiveData = MutableLiveData<List<FavoriteMoviesEntity>>()
    val popularMoviesLiveDataFromCache = MutableLiveData<List<FavoriteMoviesEntity>>()
    val isFavoriteLiveData = MutableLiveData<Boolean>()
    val searchResult = MutableLiveData<List<FavoriteMoviesEntity>>(listOf())
    val liveDataState by lazy { MutableLiveData<DataState>() }

    fun getPopularList() {
        viewModelScope.launch {
            repository.getPopularMovies {
                popularMoviesLiveData.postValue(it)
            }
        }
    }

    fun addToFavorites(movie: FavoriteMoviesEntity) {
        isFavoriteLiveData.value = true
        viewModelScope.launch {
            repository.addToFavorites(movie)
        }
    }

    fun removeFromFavorites(movie: FavoriteMoviesEntity) {
        isFavoriteLiveData.value = false
        viewModelScope.launch {
            repository.deleteFromFavorites(movie)

        }
    }

    fun getAllFavoriteMoviesFromCache(){
        viewModelScope.launch {
            repository.getAllFavorites {
                popularMoviesLiveDataFromCache.value = it
            }
        }
    }

    suspend fun searchMovieByTitle1(movieTitle: String){
        liveDataState.value = DataState.Loading
        repository.searchByTitle(movieTitle){
            post(it)
        }
    }

    fun getPagedMovies() : LiveData<PagingData<FavoriteMoviesEntity>>{
        return repository.getPopularMoviesByPage().cachedIn(viewModelScope)
    }
    private fun post(it: List<FavoriteMoviesEntity>){
        if (it.isEmpty()) {
            liveDataState.postValue(DataState.NothingToShow)
        }
        else {
            liveDataState.postValue(DataState.Success)
            searchResult.postValue(it)
        }
    }
}
