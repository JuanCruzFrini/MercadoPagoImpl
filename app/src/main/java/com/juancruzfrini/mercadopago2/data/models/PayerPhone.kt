package com.juancruzfrini.mercadopago2.data.models

import com.google.gson.annotations.SerializedName

data class PayerPhone(
    @SerializedName("area_code") val areaCode: Int,
    val number: String
)