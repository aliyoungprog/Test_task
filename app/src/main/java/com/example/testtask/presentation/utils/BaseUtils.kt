package com.example.testtask.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import com.example.testtask.data.repository.MovieRepositoryImpl

fun checkConnection(context: Context) : Boolean{
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun getDefaultPageConfig(): PagingConfig {
    return PagingConfig(pageSize = MovieRepositoryImpl.DEFAULT_PAGE_SIZE, enablePlaceholders = true)
}