package com.example.testtask.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.testtask.R
import com.example.testtask.domain.entity.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (private var items: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var movie: Movie

        fun bind(movie: Movie){
            this.movie = movie
            //bind additional atributes
            //itemView.movieOriginalTitle.text = movie.title
            itemView.movie_img.load("https://image.tmdb.org/t/p/w500${movie.img}"){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
            }
        }
    }
}