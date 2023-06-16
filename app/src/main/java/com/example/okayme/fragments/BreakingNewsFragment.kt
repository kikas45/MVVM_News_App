package com.example.okayme.fragments


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.okayme.R
import com.example.okayme.adapter.NewsAdapter

import com.example.okayme.databinding.FragmentBreakingNewsBinding
import com.example.okayme.db.ArticleDatabase
import com.example.okayme.repository.NewsRepository
import com.example.okayme.ui.NewsActivity


import com.example.okayme.ui.NewsViewModel
import com.example.okayme.ui.NewsViewModelProviderFactory
import com.example.okayme.util.Resource


class BreakingNewsFragment : Fragment() {


    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    private var rvBreakingNews:RecyclerView? = null
    private var paginationProgressBar:ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breaking_news, container, false)

        rvBreakingNews = view.findViewById(R.id.rvBreakingNews)
        paginationProgressBar = view.findViewById(R.id.paginationProgressBar)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()


        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                   hideProgressBar()
                    Toast.makeText(requireContext(), "state...  $response", Toast.LENGTH_SHORT).show()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                   hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), "An error occured: $message", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                  //  showProgressBar()
                }
            }
        })


        return view
    }

    private fun hideProgressBar() {
        paginationProgressBar!!.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar!!.visibility = View.VISIBLE
    }



    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}