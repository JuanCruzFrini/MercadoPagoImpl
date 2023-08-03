package com.juancruzfrini.mercadopago2.data.models

data class AdditionalInfo(
    val items: List<Item>,
    val payer: Payer,
    val shipments: Shipments
)