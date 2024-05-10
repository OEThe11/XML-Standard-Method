package com.example.xmlstandardmethod.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlstandardmethod.data.Resource
import com.example.xmlstandardmethod.models.ui.StandardUIModel
import com.example.xmlstandardmethod.repository.StandardRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: StandardRepository
): ViewModel(){

    private val _details = MutableStateFlow<Resource<StandardUIModel>>(Resource.Loading())
    val details: StateFlow<Resource<StandardUIModel>> = _details


    fun getDetailItem(id: Int) {
        viewModelScope.launch {
            _details.value = repository.getById(id)
        }
    }
}