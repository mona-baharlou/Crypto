package com.baharlou.crypto.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding
    private lateinit var dataCoin: CoinsData.Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataCoin = intent.getParcelableExtra<CoinsData.Data>("dataToSend")!!
        binding.toolbar.toolbar.title = dataCoin.coinInfo.name

        initUI()
    }

    private fun initUI() {
        initChart()
        initStatistics()
        initAbout()
    }

    private fun initAbout() {



    }

    private fun initStatistics() {
        binding.moduleStatistics.tvOpenAmount.text = dataCoin.dISPLAY.uSD.oPEN24HOUR
        binding.moduleStatistics.tvTodayHigh.text = dataCoin.dISPLAY.uSD.hIGH24HOUR
        binding.moduleStatistics.tvTodayLow.text = dataCoin.dISPLAY.uSD.lOW24HOUR
        binding.moduleStatistics.tvTodayChange.text = dataCoin.dISPLAY.uSD.cHANGE24HOUR
        binding.moduleStatistics.tvVolume.text = dataCoin.dISPLAY.uSD.vOLUME24HOUR
        binding.moduleStatistics.tvVolume.text = dataCoin.dISPLAY.uSD.vOLUME24HOUR
        binding.moduleStatistics.tvtotalVolume.text = dataCoin.dISPLAY.uSD.tOTALVOLUME24H
        binding.moduleStatistics.tvMarketCap.text = dataCoin.dISPLAY.uSD.mKTCAP
        binding.moduleStatistics.tvSupply.text = dataCoin.dISPLAY.uSD.sUPPLY
    }

    private fun initChart() {

    }
}