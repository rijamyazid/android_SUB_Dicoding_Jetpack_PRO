package com.example.jetpack_submissions.ui.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack_submissions.data.source.local.LocalStatus
import com.example.jetpack_submissions.data.source.local.entity.MovieEntity
import com.example.jetpack_submissions.databinding.FragmentMoviesBinding
import com.example.jetpack_submissions.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesPagingDataAdapter.MovieListener {

    private val viewModel: MoviesViewModel by activityViewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesPagingDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            moviesAdapter = MoviesPagingDataAdapter(context, this)

            viewModel.movies.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        LocalStatus.LOADING -> {
                        }
                        LocalStatus.SUCCESS -> {
                            moviesAdapter.submitList(it.data)
                            moviesAdapter.notifyDataSetChanged()
                        }
                        LocalStatus.ERROR -> {
                        }
                    }
                }
            })

            with(binding.rvMovies) {
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