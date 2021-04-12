package com.example.testtask.data.repository

import android.graphics.Movie
import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.testtask.data.network.ApiService
import com.example.testtask.data.repository.MovieRepositoryImpl.Companion.DEFAULT_PAGE_INDEX
import com.example.testtask.domain.entity.FavoriteMoviesEntity
import retrofit2.HttpException
import java.io.IOException


class MoviePagingSource(
    val movieApiService: ApiService,
) : PagingSource<Int, FavoriteMoviesEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteMoviesEntity> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = movieApiService.getPopularByPage("7c4afca12ec6c62155cbfa6647f584b7", page)
            LoadResult.Page(
                response.body()!!.items, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.body() == null) null else page + 1
            )
        }
        catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
        catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}