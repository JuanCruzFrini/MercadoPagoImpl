package com.juancruzfrini.mercadopago2.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import com.juancruzfrini.mercadopago2.R
import com.juancruzfrini.mercadopago2.databinding.FragmentPostPaymentBinding
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.internal.features.checkout.CheckoutActivity

class FragmentPostPayment : Fragment(R.layout.fragment_post_payment) {

    private lateinit var binding: FragmentPostPaymentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostPaymentBinding.bind(view)

        binding.btnFinish.setOnClickListener { closeFragment() }

        binding.txtDescription.text = arguments?.getSerializable("localCheckoutData").toString()
    }

    private fun closeFragment() {
        //CheckoutActivity().finishWithPaymentResult()
        activity?.supportFragmentManager?.fragments?.remove(this)
    }
}