package com.juancruzfrini.mercadopago2.data.models

import com.google.gson.annotations.SerializedName

data class Item(
    val id: String,
    val title: String,
    val description: String,
    @SerializedName("picture_url") val pictureUrl: String,
    @SerializedName("category_id") val categoryId: String,
    val quantity: Int,
    @SerializedName("unit_price") val unitPrice: Double
)