package com.juancruzfrini.mercadopago2.data.models

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("additional_info") val additionalInfo: AdditionalInfo,
    val description: String,
    @SerializedName("external_reference") val externalReference: String,
    val installments: Int,
    val metadata: Map<String, Any>,
    val payer: Map<String, Any>,
    @SerializedName("payment_method_id") val paymentMethodId: String,
    val token: String,
    @SerializedName("transaction_amount") val transactionAmount: Double
)

data class PaymentResult(
    @SerializedName("id") val id: Long? = 0L,
    @SerializedName("transaction_amount") val transaction_amount: Double? = 0.0,
)