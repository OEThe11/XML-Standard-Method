package com.example.xmlstandardmethod.di

import com.example.xmlstandardmethod.Constants.BASE_URL
import com.example.xmlstandardmethod.Constants.VENMO_URL
import com.example.xmlstandardmethod.network.StandardApi
import com.example.xmlstandardmethod.network.VenmoApi
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
    fun providesVenmoApi(): VenmoApi {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(VENMO_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(VenmoApi::class.java)
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