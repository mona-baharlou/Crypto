package com.baharlou.crypto.apiManager

import android.util.Log
import android.widget.Toast
import com.baharlou.crypto.apiManager.model.ChartData
import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.apiManager.model.NewsData
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager {

    private val apiService: ApiService

    init {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
            .addInterceptor {
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

    fun getCoinList(apiCallback: ApiCallback<List<CoinsData.Data>>) {
        apiService.getTopCoins().enqueue(object : Callback<CoinsData> {
            override fun onResponse(call: Call<CoinsData>, response: Response<CoinsData>) {

                val data = response.body()!!

                apiCallback.onSuccess(data.data)
            }

            override fun onFailure(call: Call<CoinsData>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }

        })
    }

    fun getChartData(
        symbol: String,
        period: String,
        apiCallback: ApiCallback<Pair<List<ChartData.Data>, ChartData.Data?>>
    ) {

        var histoPeriod = ""
        var limit = 30
        var aggregate = 1

        when (period) {

            HOUR -> {
                histoPeriod = HISTO_MINUTE
                limit = 60
                aggregate = 12
            }

            HOURS24 -> {
                histoPeriod = HISTO_HOUR
                limit = 24
            }

            MONTH -> {
                histoPeriod = HISTO_DAY
                limit = 30
            }

            MONTH3 -> {
                histoPeriod = HISTO_DAY
                limit = 90
            }

            WEEK -> {
                histoPeriod = HISTO_HOUR
                aggregate = 6
            }

            YEAR -> {
                histoPeriod = HISTO_DAY
                aggregate = 13
            }

            ALL -> {
                histoPeriod = HISTO_DAY
                aggregate = 30
                limit = 2000
            }

        }

        apiService.getChartData(histoPeriod, symbol, limit, aggregate)
            .enqueue(object : Callback<ChartData> {
                override fun onResponse(call: Call<ChartData>, response: Response<ChartData>) {
                    try {
                        val dataFull = response.body()!!
                        val data1 = dataFull.data
                        val data2 = dataFull.data.maxByOrNull { it.close.toFloat() }
                        val returningData = Pair(data1, data2)

                        apiCallback.onSuccess(returningData)
                    } catch (ex: Exception) {
                        Log.d("histoo ", "onResponse: ${ex.message}")
                    }
                }

                override fun onFailure(call: Call<ChartData>, t: Throwable) {
                    apiCallback.onError(t.message!!)
                }

            })

    }


    interface ApiCallback<T> {

        fun onSuccess(data: T)

        fun onError(errorMessage: String)
    }
}