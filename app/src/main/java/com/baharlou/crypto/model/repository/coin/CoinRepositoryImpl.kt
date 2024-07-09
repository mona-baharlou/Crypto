package com.baharlou.crypto.model.repository.coin

import android.util.Log
import com.baharlou.crypto.model.ALL
import com.baharlou.crypto.model.HISTO_DAY
import com.baharlou.crypto.model.HISTO_HOUR
import com.baharlou.crypto.model.HISTO_MINUTE
import com.baharlou.crypto.model.HOUR
import com.baharlou.crypto.model.HOURS24
import com.baharlou.crypto.model.MONTH
import com.baharlou.crypto.model.MONTH3
import com.baharlou.crypto.model.SUCCESS
import com.baharlou.crypto.model.WEEK
import com.baharlou.crypto.model.YEAR
import com.baharlou.crypto.model.data.chart.Data
import com.baharlou.crypto.model.net.ApiService
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : CoinRepository {
    override suspend fun getChartData(
        symbol: String,
        period: String
    ): Pair<List<Data>, Data?> {

        val dataToSend: Pair<List<Data>, Data?> = Pair(listOf(), Data())


        var histoPeriod = ""
        var limit = 30 //the number of data points to return
        var aggregate = 1 //time period to aggregate data over


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

        val response = apiService.getChartData(histoPeriod, symbol, limit, aggregate)

        try {
            if (response.Response == SUCCESS) {
                val dataFull = response!!
                val data1 = dataFull.Data
                val data2 = dataFull.Data.maxByOrNull { (it.close ?: 0).toFloat() }
                val returningData = Pair(data1, data2)
                return returningData
            }

        } catch (ex: Exception) {
            Log.d("histoo ", "onResponse: ${ex.message}")
        }
        return dataToSend
    }
}