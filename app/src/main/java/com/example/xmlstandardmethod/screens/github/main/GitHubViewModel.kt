package com.example.xmlstandardmethod.screens.github.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.xmlstandardmethod.network.GitHubRepo
import com.example.xmlstandardmethod.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val repository: GitHubRepository
): ViewModel(){

    private val _searchQuery = MutableStateFlow("android")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults: Flow<PagingData<GitHubRepo>> = _searchQuery
        .flatMapLatest { query ->
            repository.getSearchResults(query)
        }
        .cachedIn(viewModelScope)

    fun updateQuery(newQuery: String){
        _searchQuery.value = newQuery
    }

}