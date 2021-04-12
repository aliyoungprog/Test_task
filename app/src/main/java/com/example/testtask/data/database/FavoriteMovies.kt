package com.example.testtask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtask.domain.entity.FavoriteMoviesEntity

private lateinit var INSTANCE: FavoriteMoviesDatabase

@Database(entities = [FavoriteMoviesEntity::class], version = 1)
abstract class FavoriteMoviesDatabase : RoomDatabase(){
    abstract val favMoviesDao: FavoriteMoviesDao

}
