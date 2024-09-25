package com.example.xmlstandardmethod.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.xmlstandardmethod.network.GitHubApiService
import com.example.xmlstandardmethod.network.GitHubRepo
import retrofit2.http.Query

class GitHubPagingSource(
    private val apiService: GitHubApiService,
    private val query: String
): PagingSource<Int, GitHubRepo>() {
    override fun getRefreshKey(state: PagingState<Int, GitHubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepo> {
        val position = params.key ?: 1 // Default to page 1 if no key is provided
        return try {
            val response = apiService.searchRepositories(query, position, params.loadSize)
            val repos = response.body()?.items ?: emptyList()

            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }


}