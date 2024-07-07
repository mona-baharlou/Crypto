package com.baharlou.crypto.model.repository.market

import com.baharlou.crypto.model.ApiManager.ApiCallback
import com.baharlou.crypto.model.data.CoinsData
import com.baharlou.crypto.model.data.NewsData
import com.baharlou.crypto.model.net.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MarketRepositoryImpl(
    @Inject private val apiService: ApiService,
) : MarketRepository {
    override suspend fun getNews(): ArrayList<Pair<String, String>> {
        val dataToSend: ArrayList<Pair<String, String>> = arrayListOf()

        val newsData = apiService.getTopNews()

        return dataToSend
        //if(newsData.)
    }

    override suspend fun getCoins(): List<CoinsData.Data> {
        return listOf()
    }
}