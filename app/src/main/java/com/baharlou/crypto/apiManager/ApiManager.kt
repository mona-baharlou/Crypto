package com.baharlou.crypto.apiManager

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://min-api.cryptocompare.com/data/"
const val BASE_URL_IMAGE = "https://www.cryptocompare.com"
const val API_KEY = "a2de58965c9c6c51f61f752c951ff3fe72385f7547e1e43bed1247f40e28dacc"
const val APP_NAME = "Crypto"


class ApiManager {

    private val apiService: ApiService

    init {

        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val oldRequest = it.request()

            val newRequest = oldRequest.newBuilder()

            newRequest.addHeader("api_Key", API_KEY)

            it.proceed(newRequest.build())
        }.build()

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

    }

    interface ApiCallback<T> {

        fun onSuccess(data: T)

        fun onError(errorMessage: String)
    }
}