package com.example.xmlstandardmethod.models.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "login")
    val login: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "avatar_url")
    val avatarUrl: String
)
