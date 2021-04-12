package com.example.testtask.presentation.adapters

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testtask.R
import com.example.testtask.domain.entity.FavoriteMoviesEntity
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (private var items: List<FavoriteMoviesEntity> = listOf(), private val clickListener: MovieItemClickListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    override fun getItemCount(): Int = items.size

    fun changeData(items: List<FavoriteMoviesEntity>){
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(movie: FavoriteMoviesEntity, clickListener: MovieItemClickListener){
            itemView.movie_img.load("https://image.tmdb.org/t/p/w500${movie.img}"){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
            }
            itemView.setOnClickListener{
                clickListener.movieClicked(movie)
            }
        }
    }
}
