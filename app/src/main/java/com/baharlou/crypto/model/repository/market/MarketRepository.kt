package com.baharlou.crypto.model.repository.market

import com.baharlou.crypto.model.data.CoinsData
import com.baharlou.crypto.model.data.NewsData

interface MarketRepository {

    suspend fun getNews(): ArrayList<Pair<String, String>>
    suspend fun getCoins(): List<CoinsData.Data>
}