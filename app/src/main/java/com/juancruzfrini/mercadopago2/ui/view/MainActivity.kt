package com.juancruzfrini.mercadopago2.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juancruzfrini.mercadopago2.databinding.ActivityMainBinding
import com.juancruzfrini.mercadopago2.ui.viewmodel.MainViewModel
import com.juancruzfrini.mercadopago2.utils.splitPaymentProcessorHelper
import com.mercadopago.android.px.configuration.PaymentConfiguration
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Item
import com.mercadopago.android.px.model.Sites
import com.mercadopago.android.px.preferences.CheckoutPreference
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenMP.setOnClickListener { startPaymentLayout() }
        //getToken()
    }

    private fun getToken() {
        viewModel.getToken()

        viewModel.token.observe(this){
            binding.body.text = it.toString()
            Log.d("Token", "$it")
        }

        viewModel.status.observe(this){
            binding.status.text = it
            Log.d("Status", it.toString())
        }
    }


    private fun startPaymentLayout() {
        val checkoutPreference = CheckoutPreference.Builder(
            Sites.ARGENTINA,
            "jcfrini5@gmail.com",
            mutableListOf(
                Item.Builder("Guitarra",1, BigDecimal(8600)).setPictureUrl("https://http2.mlstatic.com/D_NQ_NP_734191-MLA70331193308_072023-O.webp").build(),
                Item.Builder("Puas",3 , BigDecimal(300)).setPictureUrl("https://101db.com.ar/12460-large_default/puas-guitarra-bajo-fender-351-shape-premium-celluloid-picks-blue-moto-medium-pack-x144u.jpg").build(),
                Item.Builder("Cuerda",2, BigDecimal(900)).setPictureUrl("https://http2.mlstatic.com/D_NQ_NP_617148-MLU70101748193_062023-O.webp").build(),
            )
        ).build()

        val paymentConfiguration = PaymentConfiguration.Builder(splitPaymentProcessorHelper).build()

         val mercadoPagoCheckout2 = MercadoPagoCheckout.Builder(
             //Fuente: https://mercadopago.github.io/px-android/v2.html#step-three
             //Mi Public Key no anda, solo este
            "TEST-ad365c37-8012-4014-84f5-6c895b3f8e0a",
             checkoutPreference,
             paymentConfiguration
         ).build()

        mercadoPagoCheckout2.startPayment(this, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val payment = data?.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT)
            Log.d("payment", data?.data.toString())
            Log.d("payment", payment.toString())
        }
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}
