package com.baharlou.crypto.features.coin

import android.graphics.RectF
import android.util.Log
import com.baharlou.crypto.apiManager.model.ChartData
import com.robinhood.spark.SparkAdapter

class
ChartAdapter(
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

    override fun hasBaseLine(): Boolean {
        return true
    }

    /* override fun getBaseLine(): Float {
         try {
             return baseLine//?.toFloat() ?: super.getBaseLine().toFloat()

         } catch (ex: Exception) {
             Log.e("getBaseLineErr", "getBaseLine:${ex.message} ")
             return ("0").toFloat()
         }
     }

     override fun getDataBounds(): RectF {
         val bounds = super.getDataBounds()

         // will 'zoom in' to the middle portion of the graph
         bounds.inset(bounds.width() / 4, bounds.height() / 4)
         return bounds
     }*/
}