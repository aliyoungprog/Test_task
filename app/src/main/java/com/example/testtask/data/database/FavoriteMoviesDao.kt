package com.example.testtask.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testtask.domain.entity.FavoriteMoviesEntity

@Dao
interface FavoriteMoviesDao {
    @Query("select * from movie")
    fun getFavoriteMovies() : List<FavoriteMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: FavoriteMoviesEntity)

    @Delete
    fun deleteMovie(movie: FavoriteMoviesEntity)
}
