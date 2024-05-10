package com.example.xmlstandardmethod.repository

import com.example.xmlstandardmethod.data.Resource
import com.example.xmlstandardmethod.mapping.StandardMapper
import com.example.xmlstandardmethod.models.ui.StandardUIModel
import com.example.xmlstandardmethod.network.StandardApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StandardRepository @Inject constructor(
    private val api: StandardApi
) {

    suspend fun standardInfo(): Resource<List<StandardUIModel>>{
        return try {
            val response = api.getAllStandardList()

            if (response.isSuccessful){
                val standardItems = response.body()?.map()
                { StandardMapper.buildFrom(it) }

                if (standardItems.isNullOrEmpty()){
                    Resource.Error("Empty Response")
                } else{
                    Resource.Success(standardItems)
                }
            } else{
                Resource.Error("API Error: ${response.message()}")
            }
        } catch (e: Exception){
            Resource.Error("Exception: ${e.message ?: "Unknown Error"}")
        }
    }


   suspend fun getById(id: Int): Resource<StandardUIModel>{
       return try {

            val response = api.getSingularItemById(id)

            if (response.isSuccessful){
                val data = response.body()
                if (data != null) {
                    val standardItem = StandardMapper.buildFrom(data)
                    Resource.Success(standardItem)
                } else {
                    Resource.Error("Data not found for the provided id: $id")
                }
            }else {
                Resource.Error("API Error: ${response.message()}")
            }
        } catch (e: Exception){
            Resource.Error("Exception: ${e.message ?: "Unknown Error"}")
        }

    }

}