package com.example.xmlstandardmethod.screens.github.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlstandardmethod.databinding.FragmentGithubBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GitHubFragment : Fragment() {

    private val viewModel: GitHubViewModel by viewModels()
    private lateinit var adapter: GitHubPagingAdapter
    private lateinit var binding: FragmentGithubBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGithubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView and adapter
        adapter = GitHubPagingAdapter()

        // Set the LayoutManager for RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Set the adapter
        binding.recyclerView.adapter = adapter

        // Collect the paginated data and submit it to the adapter
        lifecycleScope.launchWhenStarted {
            viewModel.searchResults.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        // Add LoadStateListener to show or hide the ProgressBar
        adapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        }
    }
}