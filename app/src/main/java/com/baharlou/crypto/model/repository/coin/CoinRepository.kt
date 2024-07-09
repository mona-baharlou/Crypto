package com.baharlou.crypto.model.repository.coin

import com.baharlou.crypto.model.data.chart.Data

interface CoinRepository {

    suspend fun getChartData(
        symbol: String,
        period: String
    ): Pair<List<Data>, Data?>
}