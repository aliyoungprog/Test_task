package com.example.testtask.domain.entity

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val img: String
)
