package com.example.xmlstandardmethod.screens.venmo.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlstandardmethod.data.Resource
import com.example.xmlstandardmethod.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    private val _paymentResult = MutableLiveData<Resource<String>>()
    val paymentResult: LiveData<Resource<String>> = _paymentResult

    fun initiateVenmoPayment(amount: Double) {
        viewModelScope.launch {
            _paymentResult.postValue(Resource.Loading())  // Set loading state
            try {
                val result = paymentRepository.initiatePayment(amount)
                if (result.isSuccess) {
                    _paymentResult.postValue(Resource.Success(result.getOrNull() ?: "Unknown success"))
                } else {
                    _paymentResult.postValue(Resource.Error(result.exceptionOrNull()?.message ?: "Unknown error"))
                }
            } catch (e: Exception) {
                _paymentResult.postValue(Resource.Error("Error: ${e.message}"))
            }
        }
    }
}