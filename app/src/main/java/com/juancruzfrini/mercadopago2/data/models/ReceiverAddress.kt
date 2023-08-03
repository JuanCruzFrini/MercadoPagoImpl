package com.juancruzfrini.mercadopago2.data.models

import com.google.gson.annotations.SerializedName

data class ReceiverAddress(
    @SerializedName("zip_code") val zipCode: String,
    @SerializedName("state_name") val stateName: String,
    @SerializedName("city_name") val cityName: String,
    @SerializedName("street_name") val streetName: String,
    @SerializedName("street_number") val streetNumber: Int
)