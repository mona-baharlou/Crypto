package com.baharlou.crypto.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharlou.crypto.apiManager.model.CoinAboutData
import com.baharlou.crypto.apiManager.model.CoinAboutItem
import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.databinding.ActivityCoinBinding
import com.baharlou.crypto.features.market.ABOUT_DATA
import com.baharlou.crypto.features.market.BUNDLE_DATA
import com.baharlou.crypto.features.market.COIN_DATA

class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding
    private lateinit var dataCoin: CoinsData.Data
    private lateinit var dataAboutCoin: CoinAboutItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fromBundle = intent.getBundleExtra(BUNDLE_DATA)!!

        dataCoin = fromBundle.getParcelable<CoinsData.Data>(COIN_DATA)!!
        dataAboutCoin = fromBundle.getParcelable<CoinAboutItem>(ABOUT_DATA)!!

        binding.toolbar.toolbar.title = dataCoin.coinInfo.name

        initUI()
    }

    private fun initUI() {
        initChart()
        initStatistics()
        initAbout()
    }

    private fun initAbout() {

        binding.moduleAbout.tvWebsite.text = dataAboutCoin.coinWebsite
        binding.moduleAbout.tvAboutCoin.text = dataAboutCoin.coinDesc
        binding.moduleAbout.tvGithub.text = dataAboutCoin.coinGithub
        binding.moduleAbout.tvReddit.text = dataAboutCoin.coinReddit
        binding.moduleAbout.tvTwitter.text = dataAboutCoin.coinTwitter

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