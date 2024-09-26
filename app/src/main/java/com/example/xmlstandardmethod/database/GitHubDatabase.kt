package com.example.xmlstandardmethod.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.xmlstandardmethod.models.entity.GitHubRepoEntity

@Database(entities = [GitHubRepoEntity::class], version = 1, exportSchema = false)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun gitHubRepoDao(): GitHubRepoDao
}