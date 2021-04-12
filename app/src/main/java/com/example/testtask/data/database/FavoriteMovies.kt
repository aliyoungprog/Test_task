package com.example.testtask.data.database

import android.content.Context
import androidx.databinding.library.BuildConfig
import androidx.room.*
import com.example.testtask.domain.entity.FavoriteMoviesEntity

@Database(entities = [FavoriteMoviesEntity::class], version = 2)
@TypeConverters(MovieTypeConvertor::class)
abstract class FavoriteMovies : RoomDatabase(){

    companion object {
        const val databaseName = "favorites_db"
        fun getDatabase(context: Context): FavoriteMovies {
            return Room.databaseBuilder(context, FavoriteMovies::class.java, databaseName)
                .apply {
                    if (BuildConfig.DEBUG) {
                        fallbackToDestructiveMigration()
                    }
                    allowMainThreadQueries()
                }
                .build()
        }
    }
    abstract fun getDao(): FavoriteMoviesDao
}
