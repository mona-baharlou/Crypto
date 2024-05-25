package com.baharlou.crypto.features.coin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharlou.crypto.apiManager.ApiManager
import com.baharlou.crypto.apiManager.model.ChartData
import com.baharlou.crypto.apiManager.model.CoinAboutItem
import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.databinding.ActivityCoinBinding
import com.baharlou.crypto.features.market.ABOUT_DATA
import com.baharlou.crypto.features.market.BUNDLE_DATA
import com.baharlou.crypto.features.market.COIN_DATA

private const val TWITTER_BASE_URL = "https://twitter.com/"

class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding
    private lateinit var dataCoin: CoinsData.Data
    private lateinit var dataAboutCoin: CoinAboutItem
    private var apiManager = ApiManager()

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
        binding.moduleAbout.tvTwitter.text = "@${dataAboutCoin.coinTwitter}"

        binding.moduleAbout.tvWebsite.setOnClickListener {
            openAboutWebsite(dataAboutCoin.coinWebsite.toString())
        }

        binding.moduleAbout.tvGithub.setOnClickListener {
            openAboutWebsite(dataAboutCoin.coinGithub.toString())
        }

        binding.moduleAbout.tvReddit.setOnClickListener {
            openAboutWebsite(dataAboutCoin.coinReddit.toString())
        }

        binding.moduleAbout.tvTwitter.setOnClickListener {
            openAboutWebsite(TWITTER_BASE_URL + dataAboutCoin.coinTwitter.toString())
        }
    }

    private fun openAboutWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
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

        apiManager.getChartData("BTC", "Hour", object :
            ApiManager.ApiCallback<Pair<List<ChartData.Data>, ChartData.Data?>> {
            override fun onSuccess(data: Pair<List<ChartData.Data>, ChartData.Data?>) {

                val chartAdapter =

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@CoinActivity, "Error : $errorMessage", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }
}