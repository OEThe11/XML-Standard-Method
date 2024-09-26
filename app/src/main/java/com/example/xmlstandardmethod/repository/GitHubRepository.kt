package com.example.xmlstandardmethod.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.xmlstandardmethod.database.GitHubRepoDao
import com.example.xmlstandardmethod.models.entity.GitHubRepoEntity
import com.example.xmlstandardmethod.network.GitHubApiService
import com.example.xmlstandardmethod.models.network.GitHubRepo
import com.example.xmlstandardmethod.paging.GitHubPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val apiService: GitHubApiService,
    private val gitHubRepoDao: GitHubRepoDao
) {

    // Function to get paginated data, first check the database for cached data
    fun getPaginatedData(query: String): Flow<PagingData<GitHubRepo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,  // Number of items per page
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GitHubPagingSource(apiService, query) }
        ).flow
    }

    // Save the fetched data from API (GitHubRepo) to the database as GitHubRepoEntity
    suspend fun cacheRepositories(repos: List<GitHubRepo>) {
        // Convert API model (GitHubRepo) to database entity (GitHubRepoEntity)
        val entities = repos.map {
            GitHubRepoEntity(
                id = it.id,
                name = it.name,
                fullName = it.fullName,
                description = it.description,
                stargazersCount = it.stargazersCount,
                forksCount = it.forksCount,
                avatarUrl = it.owner.avatarUrl
            )
        }
        gitHubRepoDao.clearRepos()  // Clear old data
        gitHubRepoDao.insertAll(entities)  // Insert new data into Room
    }

    // Function to get data from Room DB
    fun getCachedRepositories(): Flow<List<GitHubRepoEntity>> {
        return gitHubRepoDao.getAllRepos()
    }
}





