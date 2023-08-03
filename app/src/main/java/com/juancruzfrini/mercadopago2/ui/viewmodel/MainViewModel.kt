package com.juancruzfrini.mercadopago2.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juancruzfrini.mercadopago2.data.RestEngine
import com.juancruzfrini.mercadopago2.data.TokenResponse
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var _token: MutableLiveData<TokenResponse> = MutableLiveData()
    var token: LiveData<TokenResponse> = _token

    private var _status: MutableLiveData<String> = MutableLiveData()
    var status: LiveData<String> = _status

    fun getToken(){
        viewModelScope.launch {
            try {
                val result = RestEngine.Companion.ApiMercadoPago.retrofitService.getToken()
                //val result = RestEngine.Companion.ApiMercadoPago.retrofitService.createPayment(/*paymentRequest*/)
                _token.value = result.body() as TokenResponse
                _status.value = result.body().toString()//"Success"
            } catch (e:Exception){
                _status.value = "${e.message} ${e.cause}"
            }
        }
    }



   /* fun startPayment(activity: Activity, publicKey: String, amount: Double, description: String) {
        val checkoutIntent = Intent(activity, CheckoutActivity::class.java)
       *//* checkoutIntent.putExtra("a", publicKey)
        checkoutIntent.putExtra(MercadoPagoCheckout.EXTRA_AMOUNT, amount)
        checkoutIntent.putExtra(MercadoPagoCheckout.EXTRA_DESCRIPTION, description)*//*

        activity.startActivityForResult(checkoutIntent, MercadoPagoCheckout.PAYMENT_RESULT_CODE)
    }*/
}