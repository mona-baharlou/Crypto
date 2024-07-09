package com.baharlou.crypto.model.net

import com.baharlou.crypto.model.data.chart.ChartResponse
import com.baharlou.crypto.model.data.coin.CoinResponse
import com.baharlou.crypto.model.data.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/news/")
    suspend fun getTopNews(@Query("sortOrder") sortOrder: String = "popular"): NewsResponse//Call<NewsData>

    @GET("top/totalvolfull")
    suspend fun getTopCoins(
        @Query("tsym") toSymbol: String = "USD",
        @Query("limit") limitData: Int = 10
    ): CoinResponse

    @GET("{period}")
    suspend fun getChartData(
        @Path("period") period: String,
        @Query("fsym") fromSymbol: String, //crypto name to show
        @Query("limit") limit: Int,
        @Query("aggregate") aggregate: Int,
        @Query("tsym") toSymbol: String = "USD" //convert to usd
    ): ChartResponse


}