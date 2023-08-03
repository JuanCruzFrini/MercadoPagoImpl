package com.juancruzfrini.mercadopago2.data

import com.google.gson.annotations.SerializedName
import com.juancruzfrini.mercadopago2.data.models.Payer
import com.juancruzfrini.mercadopago2.data.models.PaymentRequest
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.PaymentData
import com.mercadopago.android.px.model.PaymentResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

class RestEngine {

    companion object {
        //WeatherApi
        private const val BASE_URL = "https://api.mercadopago.com/" /*"https://api.mercadopago.com/V1/"*/
        private const val apiKey = "120d62e051619f3a763c78bff00c8c28"

        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            //.baseUrl(BASE_URL)
            .client(client)
            //.build()


        interface MercadoPagoService{
            @Headers(
                "Content-Type: application/json",
                //"Authorization: Bearer <ENV_ACCESS_TOKEN>"
            )
            @FormUrlEncoded
            @POST("oauth/token")
            suspend fun getToken(
                @Field("client_secret") clientSecret: String = "pQQZejNh9st39Cpv5MoZCzwk6cNcjE44",
                @Field("client_id") clientId: String  = "1246423848711895",
                @Field("grant_type") grantType: String = "authorization_code",
                @Field("code") code: String = "TG-XXXXXXXX-241983636",
                @Field("redirect_uri") redirect_uri: String= "https://www.mercadopago.com"
            ): Response<TokenResponse>

            @Headers(
                "Content-Type: application/json",
                "Authorization: Bearer APP_USR-1246423848711895-041309-187d33b38515a4c9b735b853fc051a7b-241054558"
            )
            @FormUrlEncoded
            @POST("/v1/payments")
            fun createPayment(
                @Field("description") description: String = "Celu",
                @Field("installments") installments: Int = 2,
                //@Field("issuer_id") issuer_id: String = "x",
                @Field("payer") payer: Payer = Payer(email = "email@gmail.com",),
                @Field("token") token: String? ="ff8080814c11e237014c1ff593b57b4d",
                @Field("payment_method_id") method: String? ="visa",
                @Field("transaction_amount") amount: Int? = 2500,
                /*@Body request: PaymentRequest*/): Response<com.juancruzfrini.mercadopago2.data.models.PaymentResult>
            /*@Multipart
          @POST("albums/1/photos")
          suspend fun uploadPhoto(
              @Part("photo") photo: Photo,
              @Part file: MultipartBody.Part
          ): Response<Photo>

          @Multipart
          @PATCH("albums/1/photos")
          suspend fun patchPhoto(
              @Query("id")id:Int = 3,
              @Part("photo") photo: Photo,
              @Part file: MultipartBody.Part
          ): Response<Photo>

          @GET("albums/1/photos")
          suspend fun getPhotos(): Response<List<Photo>>

          @GET("all")
          suspend fun getAllCountries(@Query("fields")fields:String): Response<List<Country>>

          @GET("forecast")

          suspend fun getForecast(
              @Query("lat")lat:Double,
              @Query("lon")lon:Double,
              @Query("lang")lang:String = "es",
              @Query("appid") apiKey: String = Companion.apiKey,
              @Query("units")units:String = "metric") : Response<Forecast>
  */

            /*fun a(){
                "https://auth.mercadopago.com.ar/authorization?client_id=1246423848711895&response_type=code&platform_id=mp&redirect_uri=https://www.mercadopago.com&scope=offline_access"
            }*/
        }


        object ApiMercadoPago{
            val retrofitService : MercadoPagoService by lazy {
                retrofit.baseUrl(BASE_URL).build().create(MercadoPagoService::class.java)
            }
        }
    }
}

data class TokenBody(
    val client_secret: String?="",
    val client_id: String?="",
    val grant_type: String?="",
    val code: String?="",
    val redirect_uri: String?="",
    //val test_token: String?="",
)

data class TokenResponse(
    val access_token :String? = "",
    //val token_type :String? = "",
    /*val scope : Long? = 0L,
    val expires_in :String? = "",
    val user_id : Long? = 0L,
    val refresh_token :String? = "",
    val public_key :String? = "",
    val live_mode :Boolean = false*/
)