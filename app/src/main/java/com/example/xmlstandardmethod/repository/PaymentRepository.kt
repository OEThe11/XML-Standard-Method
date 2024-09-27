package com.example.xmlstandardmethod.repository

import com.example.xmlstandardmethod.models.venmo.network.PaymentRequest
import com.example.xmlstandardmethod.models.venmo.network.PaymentResponse
import com.example.xmlstandardmethod.network.VenmoApi
import kotlinx.coroutines.time.delay
import retrofit2.Response
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val apiService: VenmoApi
) {

    suspend fun initiatePayment(amount: Double): Result<String>{
        //To send a dummy request to the SDK
//        return apiService.createPayment(PaymentRequest(amount = amount))

        // Randomly simulate success or failure
        return if (Math.random() > 0.5) {
            Result.success("Transaction ID: 123ABC")
        } else {
            Result.failure(Exception("Payment failed due to insufficient funds."))
        }
    }

}