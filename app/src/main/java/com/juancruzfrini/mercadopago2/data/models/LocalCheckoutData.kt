package com.juancruzfrini.mercadopago2.data.models

import android.os.Parcel
import android.os.Parcelable
import com.mercadopago.android.px.configuration.PaymentConfiguration
import com.mercadopago.android.px.core.SplitPaymentProcessor
import com.mercadopago.android.px.model.PaymentData
import com.mercadopago.android.px.preferences.CheckoutPreference

data class LocalCheckoutData(
    val paymentDataList: List<PaymentData>?,
    val checkoutPreference: CheckoutPreference,
    val securityType: String
) : java.io.Serializable

fun SplitPaymentProcessor.CheckoutData.toLocalCheckoutData(): LocalCheckoutData {
    return LocalCheckoutData(
        paymentDataList = this.paymentDataList,
        checkoutPreference = this.checkoutPreference,
        securityType = this.securityType
    )
}