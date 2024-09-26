package com.example.xmlstandardmethod.models.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubRepo(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "full_name")
    val fullName: String,

    @Json(name = "owner")
    val owner: Owner,

    @Json(name = "html_url")
    val htmlUrl: String,

    @Json(name = "description")
    val description: String?,

    @Json(name = "stargazers_count")
    val stargazersCount: Int,

    @Json(name = "forks_count")
    val forksCount: Int,

    @Json(name = "language")
    val language: String?
)
