package com.baharlou.crypto.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val dataCoin = intent.getParcelableExtra<CoinsData.Data>("dataToSend")!!

        binding.moduleStatistics.tvOpenAmount.text = "${dataCoin.dISPLAY.uSD.oPEN24HOUR}"
        binding.moduleStatistics.tvTodayHigh.text = "${dataCoin.dISPLAY.uSD.hIGH24HOUR}"
        binding.moduleStatistics.tvTodayLow.text = "${dataCoin.dISPLAY.uSD.lOW24HOUR}"
        binding.moduleStatistics.tvTodayChange.text = "${dataCoin.dISPLAY.uSD.cHANGE24HOUR}"
        binding.moduleStatistics.tvVolume.text = "${dataCoin.dISPLAY.uSD.vOLUME24HOUR}"

        binding.moduleStatistics.tvVolume.text = "${dataCoin.dISPLAY.uSD.vOLUME24HOUR}"
        binding.moduleStatistics.tvAverageVolume.text = "${dataCoin.dISPLAY.uSD.vOLUMEDAYTO}"
        binding.moduleStatistics.tvMarketCap.text = "${dataCoin.dISPLAY.uSD.mARKET}"
        binding.moduleStatistics.tvSupply.text = "${dataCoin.dISPLAY.uSD.sUPPLY}"
    }

    private fun initChart() {

    }
}