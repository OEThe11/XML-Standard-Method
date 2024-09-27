package com.example.xmlstandardmethod.network

import com.example.xmlstandardmethod.models.venmo.network.PaymentRequest
import com.example.xmlstandardmethod.models.venmo.network.PaymentResponse
import com.example.xmlstandardmethod.network.Endpoints.VENMO_ENDPOINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface VenmoApi {
    @POST(VENMO_ENDPOINT)
    suspend fun createPayment(
        @Body paymentRequest: PaymentRequest
    ): Response<PaymentResponse>


}