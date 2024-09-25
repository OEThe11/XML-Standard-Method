package com.example.xmlstandardmethod.network

import com.example.xmlstandardmethod.network.Endpoints.GITHUB_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface GitHubApiService {
    @GET(GITHUB_ENDPOINT)
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<GitHubResponse>
}