package com.example.xmlstandardmethod.mapping

import com.example.xmlstandardmethod.models.network.GetJsonResponseItem
import com.example.xmlstandardmethod.models.ui.StandardUIModel

object StandardMapper {

    fun buildFrom(response: GetJsonResponseItem): StandardUIModel{
        return StandardUIModel(
            completed = response.completed,
            id = response.id,
            title = response.title,
            userId = response.userId
        )
    }
}