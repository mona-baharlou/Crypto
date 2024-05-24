package com.baharlou.crypto.apiManager

import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.apiManager.model.NewsData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("v2/news/")
    fun getTopNews(@Query("sortOrder") sortOrder: String = "popular"): Call<NewsData>

    @GET("top/totalvolfull")
    fun getTopCoins(
        @Query("tsym") toSymbol: String = "USD",
        @Query("limit") limitData: Int = 10
    ): Call<CoinsData>




}