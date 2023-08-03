package com.juancruzfrini.mercadopago2.utils

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.juancruzfrini.mercadopago2.data.models.toLocalCheckoutData
import com.juancruzfrini.mercadopago2.ui.view.FragmentPostPayment
import com.mercadopago.android.px.core.SplitPaymentProcessor
import com.mercadopago.android.px.model.IPaymentDescriptor
import com.mercadopago.android.px.model.exceptions.MercadoPagoError
import com.mercadopago.android.px.preferences.CheckoutPreference

val splitPaymentProcessorHelper = object : SplitPaymentProcessor {

    override fun writeToParcel(p0: Parcel, p1: Int) {}
    override fun supportsSplitPayment(p0: CheckoutPreference?): Boolean = false
    override fun describeContents(): Int = Parcelable.CONTENTS_FILE_DESCRIPTOR
    override fun getPaymentTimeout(p0: CheckoutPreference): Int = 5000
    override fun shouldShowFragmentOnPayment(p0: CheckoutPreference): Boolean = true

    override fun getFragment(
        p0: SplitPaymentProcessor.CheckoutData,
        p1: Context
    ): Fragment? {

        val args = Bundle()
        args.putSerializable("localCheckoutData", p0.toLocalCheckoutData())

        val fragment = FragmentPostPayment()
        fragment.arguments = args

        return fragment
    }

    override fun startPayment(
        p0: Context,
        p1: SplitPaymentProcessor.CheckoutData,
        p2: SplitPaymentProcessor.OnPaymentListener
    ) {
        p2.onPaymentFinished(object : IPaymentDescriptor {
            override fun getPaymentStatus(): String = paymentStatus
            override fun getPaymentStatusDetail(): String = paymentStatusDetail
            override fun getId(): Long? = id ?: 0L
            override fun getStatementDescription(): String? = statementDescription
            override fun getPaymentTypeId(): String? = paymentTypeId
            override fun getPaymentMethodId(): String? = this.paymentMethodId
        })
        p2.onPaymentError(MercadoPagoError("Error", "detalle", false))

        //CheckoutActivity().finishWithPaymentResult()
        Toast.makeText(p0, "Comenzo el pago", Toast.LENGTH_SHORT).show()
    }
}
