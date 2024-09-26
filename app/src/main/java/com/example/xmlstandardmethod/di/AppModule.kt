package com.example.xmlstandardmethod.di

import android.content.Context
import androidx.room.Room
import com.example.xmlstandardmethod.Constants.BASE_URL
import com.example.xmlstandardmethod.Constants.GITHUB_URL
import com.example.xmlstandardmethod.database.GitHubDatabase
import com.example.xmlstandardmethod.database.GitHubRepoDao
import com.example.xmlstandardmethod.network.GitHubApiService
import com.example.xmlstandardmethod.network.StandardApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): GitHubDatabase {
        return Room.databaseBuilder(appContext, GitHubDatabase::class.java, "github_db").build()
    }

    @Provides
    fun provideGitHubRepoDao(database: GitHubDatabase): GitHubRepoDao {
        return database.gitHubRepoDao()
    }

    @Provides
    @Singleton
    fun providesGitHubApiService(): GitHubApiService {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(GITHUB_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GitHubApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesStandardApi(): StandardApi {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(StandardApi::class.java)
    }

}