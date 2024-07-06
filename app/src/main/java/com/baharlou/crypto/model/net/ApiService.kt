package com.baharlou.crypto.model.net

import com.baharlou.crypto.model.data.ChartData
import com.baharlou.crypto.model.data.CoinsData
import com.baharlou.crypto.model.data.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/news/")
    fun getTopNews(@Query("sortOrder") sortOrder: String = "popular"): Call<NewsData>

    @GET("top/totalvolfull")
    fun getTopCoins(
        @Query("tsym") toSymbol: String = "USD",
        @Query("limit") limitData: Int = 10
    ): Call<CoinsData>

    @GET("{period}")
    fun getChartData(
        @Path("period") period :String ,
        @Query("fsym") fromSymbol :String , //crypto name to show
        @Query("limit") limit :Int ,
        @Query("aggregate")  aggregate:Int ,
        @Query("tsym") toSymbol :String = "USD" //convert to usd
    ) :Call<ChartData>


}