package com.example.jetpack_submissions.ui.home.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack_submissions.R
import com.example.jetpack_submissions.data.MovieEntity
import com.example.jetpack_submissions.databinding.FragmentMoviesBinding
import com.example.jetpack_submissions.ui.detail.DetailActivity
import com.example.jetpack_submissions.ui.home.HomeFragmentDirections
import com.example.jetpack_submissions.ui.home.MovieListener

class MoviesFragment : Fragment(), MovieListener {

    lateinit var viewModel: MoviesViewModel
    lateinit var binding: FragmentMoviesBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MoviesViewModel::class.java]
            val movies = viewModel.getDataMovies()

            val moviesAdapter = MoviesAdapter(context, this)
            moviesAdapter.setMovies(movies)
            with(binding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun movieOnClick(entity: MovieEntity) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailActivity(entity)
        findNavController().navigate(action)
    }
}