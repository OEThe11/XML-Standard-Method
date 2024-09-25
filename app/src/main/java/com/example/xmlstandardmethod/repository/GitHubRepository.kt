package com.example.xmlstandardmethod.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.xmlstandardmethod.network.GitHubApiService
import com.example.xmlstandardmethod.network.GitHubRepo
import com.example.xmlstandardmethod.paging.GitHubPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val apiService: GitHubApiService) {

    fun getSearchResults(query: String): Flow<PagingData<GitHubRepo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GitHubPagingSource(apiService, query) }
        ).flow
    }

}