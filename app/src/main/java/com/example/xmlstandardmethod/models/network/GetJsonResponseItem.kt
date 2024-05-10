package com.example.xmlstandardmethod.models.network

data class GetJsonResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)