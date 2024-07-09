package com.baharlou.crypto.model.repository.market

import android.util.Log
import com.baharlou.crypto.model.SUCCESS
import com.baharlou.crypto.model.data.coin.Data
import com.baharlou.crypto.model.net.ApiService
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : MarketRepository {
    override suspend fun getNews(): ArrayList<Pair<String, String>> {
        val dataToSend: ArrayList<Pair<String, String>> = arrayListOf()
        try {
            val newsResponse = apiService.getTopNews()

            if (newsResponse.Type == 100) {

                newsResponse.Data.forEach {
                    dataToSend.add(Pair(it.title ?: "", it.url ?: ""))
                }
            }
        } catch (ex: Exception) {
            Log.d("newsException ", "getNews: ${ex.message}")
        }
        return dataToSend
    }

    override suspend fun getCoins(): ArrayList<Data> {

        val coinResponse = apiService.getTopCoins()
        if (coinResponse.Message == SUCCESS) {
            return coinResponse.Data
        }
        return arrayListOf()
    }
}