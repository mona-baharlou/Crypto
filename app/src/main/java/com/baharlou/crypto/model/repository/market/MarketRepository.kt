package com.baharlou.crypto.model.repository.market

import com.baharlou.crypto.model.data.coin.Data

interface MarketRepository {

    suspend fun getNews(): ArrayList<Pair<String, String>>
    suspend fun getCoins(): List<Data>
}