package com.juancruzfrini.mercadopago2.data.models

import com.google.gson.annotations.SerializedName

data class Payer(
    @SerializedName("email") val email: String? ="",
    @SerializedName("entity_type") val entity_type: String? = "individual",
    @SerializedName("type") val type: String? ="guest",
    //val phone: PayerPhone,
    //val address: Map<String, Any>
)