package com.example.testtask.presentation.fragments

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.filter
import com.example.testtask.R
import com.example.testtask.databinding.FragmentHomeBinding
import com.example.testtask.domain.entity.FavoriteMoviesEntity
import com.example.testtask.presentation.adapters.MovieAdapter
import com.example.testtask.presentation.adapters.MovieItemClickListener
import com.example.testtask.presentation.adapters.PagingAdapter
import com.example.testtask.presentation.utils.DataState
import com.example.testtask.presentation.utils.checkConnection
import com.example.testtask.presentation.view_models.MovieViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import org.koin.android.ext.android.inject
import kotlin.coroutines.coroutineContext

class HomeFragment : Fragment(), MovieItemClickListener {
    private lateinit var bind: FragmentHomeBinding
    private val movieViewModel: MovieViewModel by inject()
    private val adapter = MovieAdapter(clickListener = this)
    private val pagingAdapter = PagingAdapter()

    companion object{
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)
        bind = FragmentHomeBinding.inflate(inflater)
        return bind.root
    }

    override fun onStart() {
        super.onStart()
        movieViewModel.getAllFavoriteMoviesFromCache()
        if (movieViewModel.popularMoviesLiveDataFromCache.value?.size!! > 0){
            setActionBarTitle("Избранные")
            movieViewModel.popularMoviesLiveDataFromCache.observe(viewLifecycleOwner, Observer {
                adapter.changeData(it)
                bind.movieRecycler.adapter = adapter
            })
        }else{
            if (!checkConnection(requireContext())) {
                Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
            }
            else {
                movieViewModel.getPopularList()
                //loadAllByPage()
                setActionBarTitle("Популярные")
                bind.progressCircular.visibility = View.VISIBLE
                movieViewModel.popularMoviesLiveData.observe(viewLifecycleOwner, Observer {
                    bind.progressCircular.visibility = View.GONE
                    adapter.changeData(it)
                    bind.movieRecycler.adapter = adapter
                })
            }
        }
    }


    override fun movieClicked(movie: FavoriteMoviesEntity) {
        val bundle = Bundle()
        val fragment = MovieDescriptionFragment.newInstance()
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        bundle.putParcelable("get_movie", movie)
        fragment.arguments = bundle
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.commit()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearch(query) // IO thread
                this@HomeFragment.hideKeyboard()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun setActionBarTitle(title: String){
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    private fun observeSearchView(){
        movieViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            adapter.changeData(items = movieViewModel.searchResult.value!!)
            bind.movieRecycler.adapter = adapter
        })
    }

    private fun getSearch(query: String?){
        lifecycleScope.launch {
            movieViewModel.searchMovieByTitle1(query!!)
            bind.progressCircular.visibility = View.VISIBLE
            if (movieViewModel.liveDataState.value == DataState.Success){
                bind.progressCircular.visibility = View.GONE
                observeSearchView()
            }else{
                Toast.makeText(context, "Результатов не найдено!", Toast.LENGTH_SHORT).show()
                bind.progressCircular.visibility = View.GONE
            }
        }
    }

    private fun loadAllByPage(){
        movieViewModel.getPagedMovies().observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                pagingAdapter.submitData(it)
            }
        })
    }
}
