package com.example.testtask.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.data.network.ApiRetrofit
import com.example.testtask.databinding.FragmentHomeBinding
import com.example.testtask.presentation.adapters.MovieAdapter
import com.example.testtask.presentation.view_models.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class HomeFragment : Fragment() {

    private lateinit var bind: FragmentHomeBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        bind = FragmentHomeBinding.inflate(inflater)
        return bind.root
    }

    override fun onStart() {
        super.onStart()
        val api = ApiRetrofit.injectApiService()
        CoroutineScope(Dispatchers.IO).launch {
            val r = api.getPopular("7c4afca12ec6c62155cbfa6647f584b7")
            withContext(Dispatchers.Main) {
                try {
                    if (r.isSuccessful) {
                        adapter = MovieAdapter(r.body()?.items!!)
                        bind.movieRecycler.adapter = adapter
                    }
                } catch (e: HttpException) {
                    Toast.makeText(activity, e.message(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}