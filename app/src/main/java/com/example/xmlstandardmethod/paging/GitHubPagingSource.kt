package com.example.xmlstandardmethod.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.xmlstandardmethod.network.GitHubApiService
import com.example.xmlstandardmethod.models.network.GitHubRepo

class GitHubPagingSource(
    private val apiService: GitHubApiService,
    private val query: String
): PagingSource<Int, GitHubRepo>() {     // PagingSource takes two generic types: Key (Int for page number) and Data (GitHubRepo)
    override fun getRefreshKey(state: PagingState<Int, GitHubRepo>): Int? {
        // Provide the key for the page to load when refresh is triggered (e.g., when swipe-to-refresh is used)
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepo> {
        val position = params.key ?: 1 // Default to page 1 if no key is provided
        return try {
            // API call to search for repositories with the given query, page number, and page size
            val response = apiService.searchRepositories(query, position, params.loadSize)
            val repos = response.body()?.items ?: emptyList() // Extract the list of repositories from the response

            // Return the successful LoadResult with the data and keys for previous and next pages
            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else position - 1, // No previous page if on the first page
                // Calculate the next page number if there are more items to load
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }


}