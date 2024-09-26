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
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlstandardmethod.databinding.FragmentGithubBinding
import com.example.xmlstandardmethod.models.entity.GitHubRepoEntity
import com.example.xmlstandardmethod.models.network.GitHubRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GitHubFragment : Fragment() {

    private val viewModel: GitHubViewModel by viewModels()
    private lateinit var adapter: GitHubPagingAdapter
    private lateinit var binding: FragmentGithubBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GitHubPagingAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Collect paginated data from the API and cache it
        lifecycleScope.launchWhenStarted {
            viewModel.paginatedData.collectLatest { pagingData ->
                adapter.submitData(pagingData)

                // Convert PagingData<GitHubRepo> to List<GitHubRepo> and pass to cacheRepositories
                val repoList = pagingData.collectData() // Collects and extracts data from PagingData

                // Cache the API data
                viewModel.cacheRepositories(repoList)
            }
        }

        // Collect cached data from Room and display it when available
        lifecycleScope.launchWhenStarted {
            viewModel.cachedRepositories.collect { repos ->
                if (repos.isNotEmpty()) {
                    // Handle displaying cached data
                }
            }
        }
    }

    // Helper function to convert PagingData into List
    private suspend fun PagingData<GitHubRepo>.collectData(): List<GitHubRepo> {
        val repoList = mutableListOf<GitHubRepo>()
        this.map { repo ->
            repoList.add(repo)
        }
        return repoList
    }
}