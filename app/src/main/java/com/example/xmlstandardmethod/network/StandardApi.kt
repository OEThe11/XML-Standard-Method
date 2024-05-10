package com.example.xmlstandardmethod.network

import com.example.xmlstandardmethod.models.network.GetJsonResponseItem
import com.example.xmlstandardmethod.network.Endpoints.ENDPOINT1
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface StandardApi {

    @GET(ENDPOINT1)
    suspend fun getAllStandardList(): Response<List<GetJsonResponseItem>>

    @GET("${ENDPOINT1}/{id}")
    suspend fun getSingularItemById(@Path("id") id: Int): Response<GetJsonResponseItem>
}