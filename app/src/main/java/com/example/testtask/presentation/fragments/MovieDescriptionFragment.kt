package com.example.testtask.presentation.fragments


import android.content.Context
import android.graphics.Movie
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testtask.R
import com.example.testtask.databinding.FragmentMovieDescriptionBinding
import com.example.testtask.domain.entity.FavoriteMoviesEntity
import com.example.testtask.presentation.view_models.MovieViewModel
import org.koin.android.ext.android.inject


class MovieDescriptionFragment : Fragment() {

    lateinit var bind: FragmentMovieDescriptionBinding
    lateinit var btnAdd: ImageView
    lateinit var movieTitle: TextView
    lateinit var movieDesc: TextView
    lateinit var movie: FavoriteMoviesEntity
    private val favoriteMoviesViewModel: MovieViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        setUpActionBar()
        bind = FragmentMovieDescriptionBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBindings()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieDescriptionFragment()
    }

    private fun setUpBindings(){
        val bundle: Bundle? = this.arguments
        setUpViews()
        movie = bundle!!.getParcelable("get_movie")!!
        setImg(movie)
        movieTitle.text = movie.title
        movieDesc.text = movie.overview
        addBtnListener(movie)
    }

    private fun setUpViews(){
        movieTitle = bind.movieTitle
        movieDesc = bind.movieDescription
        btnAdd = bind.addToFavorites
    }

    private fun addBtnListener(movie: FavoriteMoviesEntity){
        btnAdd.setOnClickListener {
            if (!movie.isFavorite) {
                favoriteMoviesViewModel.addToFavorites(movie)
                bind.addToFavorites.setImageResource(R.drawable.ic_star_filled)
                Toast.makeText(activity, "Add ${movie.title}", Toast.LENGTH_SHORT).show()
            }
            else {
                favoriteMoviesViewModel.removeFromFavorites(movie)
                bind.addToFavorites.setImageResource(R.drawable.ic_star_border)
                Toast.makeText(activity, "Remove ${movie.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setImg(movie: FavoriteMoviesEntity){
        if (movie.isFavorite){
            bind.addToFavorites.setImageResource(R.drawable.ic_star_filled)
        }else{
            bind.addToFavorites.setImageResource(R.drawable.ic_star_border)
        }
    }

    private fun setUpActionBar() {
        (activity as AppCompatActivity).supportActionBar?.title = "Описание"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            val transaction = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, HomeFragment.newInstance())
            transaction.commit()
            return true
        }
        return super.onOptionsItemSelected(item);
    }
}