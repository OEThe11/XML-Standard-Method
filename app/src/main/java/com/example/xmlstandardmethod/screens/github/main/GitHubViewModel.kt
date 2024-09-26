package com.example.xmlstandardmethod.screens.github.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.xmlstandardmethod.models.entity.GitHubRepoEntity
import com.example.xmlstandardmethod.models.network.GitHubRepo
import com.example.xmlstandardmethod.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    // LiveData for displaying cached data from the database
    val cachedRepositories: Flow<List<GitHubRepoEntity>> = repository.getCachedRepositories()

    // Get the latest paginated data from the API and cache it
    val paginatedData: Flow<PagingData<GitHubRepo>> = repository.getPaginatedData("android")
        .cachedIn(viewModelScope)

    // Save the data fetched from the API into the local Room database
    fun cacheRepositories(repos: List<GitHubRepo>) {
        viewModelScope.launch {
            repository.cacheRepositories(repos)
        }
    }
}