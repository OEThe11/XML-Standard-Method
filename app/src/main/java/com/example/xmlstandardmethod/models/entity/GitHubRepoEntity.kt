package com.example.xmlstandardmethod.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_repos")
data class GitHubRepoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val stargazersCount: Int,
    val forksCount: Int,
    val avatarUrl: String
)