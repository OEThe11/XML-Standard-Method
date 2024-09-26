package com.example.xmlstandardmethod.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xmlstandardmethod.models.entity.GitHubRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GitHubRepoDao {

    // Insert a list of repositories, replacing existing ones if they have the same ID
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<GitHubRepoEntity>): List<Long>

    // Fetch all repositories from the database
    @Query("SELECT * FROM github_repos")
    fun getAllRepos(): Flow<List<GitHubRepoEntity>> // Returns row IDs of inserted items

    // Clear the table (useful for clearing old data)
    @Query("DELETE FROM github_repos")
    suspend fun clearRepos(): Int // Returns the number of rows deleted
}