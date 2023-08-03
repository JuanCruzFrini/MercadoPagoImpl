package com.juancruzfrini.mercadopago2.data.models

import com.google.gson.annotations.SerializedName

data class Shipments(
    @SerializedName("receiver_address") val receiverAddress: ReceiverAddress
)