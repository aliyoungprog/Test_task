package com.example.testtask.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.domain.entity.FavoriteMoviesEntity

class PagingAdapter: PagingDataAdapter<FavoriteMoviesEntity, RecyclerView.ViewHolder>(
    REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<FavoriteMoviesEntity>() {
            override fun areItemsTheSame(oldItem: FavoriteMoviesEntity, newItem: FavoriteMoviesEntity): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: FavoriteMoviesEntity, newItem: FavoriteMoviesEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MyViewHolder)?.bind(movie = getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder.getInstance(parent)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun getInstance(parent: ViewGroup): MyViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_movie, parent, false)
                return MyViewHolder(view)
            }
        }


        fun bind(movie: FavoriteMoviesEntity) {

        }

    }

}