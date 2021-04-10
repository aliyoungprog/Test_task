package com.example.testtask.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R
import com.example.testtask.presentation.view_models.FavoritesViewModel

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }
}