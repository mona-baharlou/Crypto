package com.baharlou.crypto.features.coin

import com.baharlou.crypto.apiManager.model.ChartData
import com.robinhood.spark.SparkAdapter

class ChartAdapter(
    private val historicalData: List<ChartData.Data>,
    private val baseline: String?
) : SparkAdapter() {
    override fun getCount(): Int {
        return historicalData.size
    }

    override fun getItem(index: Int): ChartData.Data {
        return historicalData[index]
    }

    override fun getY(index: Int): Float {
        return historicalData[index].close.toFloat()
    }
}