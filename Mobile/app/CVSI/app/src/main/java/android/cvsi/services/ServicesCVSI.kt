package android.cvsi.services

import android.cvsi.models.request.LoginClientRequest
import android.cvsi.models.request.MessageSaveClientRequest
import android.cvsi.models.request.ProductCreateClientRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface ServicesCVSI {
    //conversation-controller

    @GET("/conversation")
    fun getConversation():Call<Any>

    @POST("/conversation/{productId}")
    fun postConversation():Call<Any>

    //image-controller

    @GET("/image/{imageId}")
    fun getImage():Call<Any>

    //login-controller

    @POST("/login")
    fun authenticationRequest():Call<LoginClientRequest>

    //message-controller


    @GET("/message/{conversationId}")
    fun getMessage():Call<Any>

    @POST("/message/{conversationId}")
    fun postMessage():Call<MessageSaveClientRequest>

    //product-controller

    @GET("/product")
    fun getProduct():Call<Any>

    @POST("/product")
    fun postProduct():Call<ProductCreateClientRequest>




//    @GET("forecast?APPID=$API_KEY_FREE_WEATHER")
//    fun getForecast(
//            @Query("lon") lon: Double,
//            @Query("lat") lat: Double,
//            @Query("lang") lang:String
//    ): Call<WeatherForecast>
}

