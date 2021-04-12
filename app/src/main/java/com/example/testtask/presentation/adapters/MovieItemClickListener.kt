package com.example.testtask.presentation.adapters

import com.example.testtask.domain.entity.FavoriteMoviesEntity

interface MovieItemClickListener {
    fun movieClicked(movie: FavoriteMoviesEntity)
}