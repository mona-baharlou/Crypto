package com.baharlou.crypto.model.repository.market

import com.baharlou.crypto.model.SUCCESS
import com.baharlou.crypto.model.data.CoinsData
import com.baharlou.crypto.model.data.coin.CoinResponse
import com.baharlou.crypto.model.data.coin.Data
import com.baharlou.crypto.model.net.ApiService
import javax.inject.Inject

class MarketRepositoryImpl(
    @Inject private val apiService: ApiService,
) : MarketRepository {
    override suspend fun getNews(): ArrayList<Pair<String, String>> {
        val dataToSend: ArrayList<Pair<String, String>> = arrayListOf()

        val newsResponse = apiService.getTopNews()
        if (newsResponse.Type == 100) {

            newsResponse.Data.forEach {
                dataToSend.add(Pair(it.title ?: "", it.url ?: ""))
            }
        }

        return dataToSend
    }

    override suspend fun getCoins(): ArrayList<Data> {

        val coinResponse = apiService.getTopCoins()
        if (coinResponse.Message == SUCCESS) {
            return coinResponse.Data
        }
        return arrayListOf(Data())
    }
}