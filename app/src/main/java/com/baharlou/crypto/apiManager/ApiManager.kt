package com.baharlou.crypto.apiManager

import com.baharlou.crypto.apiManager.model.NewsData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://min-api.cryptocompare.com/data/"
const val BASE_URL_IMAGE = "https://www.cryptocompare.com"
const val API_KEY = "a2de58965c9c6c51f61f752c951ff3fe72385f7547e1e43bed1247f40e28dacc"
const val API_KEY1 =
    "authorization: ApiKey a2de58965c9c6c51f61f752c951ff3fe72385f7547e1e43bed1247f40e28dacc"
const val APP_NAME = "Crypto"


class ApiManager {

    private val apiService: ApiService

    init {

        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val oldRequest = it.request()

            val newRequest = oldRequest.newBuilder()

            //newRequest.addHeader("ApiKey", API_KEY)
            newRequest.addHeader("authorization", API_KEY)

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

    fun getNews(apiCallback: ApiCallback<ArrayList<Pair<String, String>>>) {
        apiService.getTopNews().enqueue(object : Callback<NewsData> {
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                val data = response.body()!!

                val dataToSend: ArrayList<Pair<String, String>> = arrayListOf()
                data.data.forEach {
                    dataToSend.add(Pair(it.title, it.url))
                }
                apiCallback.onSuccess(dataToSend)

            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }

        })
    }

    interface ApiCallback<T> {

        fun onSuccess(data: T)

        fun onError(errorMessage: String)
    }
}