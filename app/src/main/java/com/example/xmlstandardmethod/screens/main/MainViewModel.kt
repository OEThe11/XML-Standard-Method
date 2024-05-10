package com.example.xmlstandardmethod.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlstandardmethod.data.Resource
import com.example.xmlstandardmethod.models.ui.StandardUIModel
import com.example.xmlstandardmethod.repository.StandardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: StandardRepository
): ViewModel(){

    private val _standardList = MutableStateFlow<Resource<List<StandardUIModel>>>(Resource.Loading())
    val standardList: StateFlow<Resource<List<StandardUIModel>>> = _standardList

    fun getStandardList() {
        viewModelScope.launch {
            _standardList.value = repository.standardInfo()
        }
    }

}