package com.example.testtask.domain.entity

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val items: List<Movie> = emptyList(),
    val nextPage: Int? = null
)
