package com.baharlou.crypto.features.coin

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.baharlou.crypto.R
import com.baharlou.crypto.apiManager.ALL
import com.baharlou.crypto.apiManager.ApiManager
import com.baharlou.crypto.apiManager.HOUR
import com.baharlou.crypto.apiManager.HOURS24
import com.baharlou.crypto.apiManager.MONTH
import com.baharlou.crypto.apiManager.MONTH3
import com.baharlou.crypto.apiManager.WEEK
import com.baharlou.crypto.apiManager.YEAR
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
        try {
            val fromBundle = intent.getBundleExtra(BUNDLE_DATA)!!

            dataCoin = fromBundle.getParcelable<CoinsData.Data>(COIN_DATA)!!

            if (fromBundle.getParcelable<CoinAboutItem>(ABOUT_DATA) != null) {
                dataAboutCoin = fromBundle.getParcelable<CoinAboutItem>(ABOUT_DATA)!!
            } else {
                dataAboutCoin = CoinAboutItem()
            }
        } catch (ex: Exception) {
            Toast.makeText(this, "excep: ${ex.message}", Toast.LENGTH_SHORT).show()


            binding.toolbar.toolbar.title = dataCoin.coinInfo.name
        }
        initUI()
    }

    private fun initUI() {
        try {
            initChart()
        } catch (ex: Exception) {
            Toast.makeText(this, "exce11p: ${ex.message}", Toast.LENGTH_SHORT).show()
        }

        try {

            initStatistics()
        } catch (ex: Exception) {
            Toast.makeText(this, "ex22cep: ${ex.message}", Toast.LENGTH_SHORT).show()
        }

        try {
            initAbout()
        } catch (ex: Exception) {
            Toast.makeText(this, "exc33ep: ${ex.message}", Toast.LENGTH_SHORT).show()
        }

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

    @SuppressLint("SetTextI18n")
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

    @SuppressLint("SetTextI18n")
    private fun initChart() {

        var period: String = HOUR

        requestAndShowChart(period)

        binding.moduleChart.radioGroupMain.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.radio_12h -> {
                    period = HOUR
                }

                R.id.radio_1d -> {
                    period = HOURS24
                }

                R.id.radio_1w -> {
                    period = WEEK
                }

                R.id.radio_1m -> {
                    period = MONTH
                }

                R.id.radio_3m -> {
                    period = MONTH3
                }

                R.id.radio_1y -> {
                    period = YEAR
                }

                R.id.radio_all -> {
                    period = ALL
                }


            }
            requestAndShowChart(period)
        }

        binding.moduleChart.txtChartPrice.text = dataCoin.dISPLAY.uSD.pRICE
        binding.moduleChart.txtChartChange1.text = " " + dataCoin.dISPLAY.uSD.cHANGE24HOUR

        if (dataCoin.coinInfo.fullName == "BUSD") {
            binding.moduleChart.txtChartChange2.text = "0%"
        } else {
            binding.moduleChart.txtChartChange2.text =
                dataCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 3) + "%"
            //dataCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 5) + "%"
        }

        val taghir = dataCoin.rAW.uSD.cHANGEPCT24HOUR
        if (taghir > 0) {

            binding.moduleChart.txtChartChange2.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorGain
                )
            )

            binding.moduleChart.txtChartUpdown.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorGain
                )
            )

            binding.moduleChart.txtChartUpdown.text = "▲"

            binding.moduleChart.sparkMain.lineColor = ContextCompat.getColor(
                binding.root.context,
                R.color.colorGain
            )

        } else if (taghir < 0) {

            binding.moduleChart.txtChartChange2.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorLoss
                )
            )

            binding.moduleChart.txtChartUpdown.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorLoss
                )
            )

            binding.moduleChart.txtChartUpdown.text = "▼"

            binding.moduleChart.sparkMain.lineColor = ContextCompat.getColor(
                binding.root.context,
                R.color.colorLoss
            )


        }

        binding.moduleChart.sparkMain.setScrubListener {

            // show price kamel
            if (it == null) {
                binding.moduleChart.txtChartPrice.text = dataCoin.dISPLAY.uSD.pRICE
            } else {
                // show price this dot
                binding.moduleChart.txtChartPrice.text =
                    "$ " + (it as ChartData.Data).close.toString()
            }

        }
    }

    private fun requestAndShowChart(period: String) {
        apiManager.getChartData(dataCoin.coinInfo.name, period, object :
            ApiManager.ApiCallback<Pair<List<ChartData.Data>, ChartData.Data?>> {
            override fun onSuccess(data: Pair<List<ChartData.Data>, ChartData.Data?>) {
                try {
                    val chartAdapter = ChartAdapter(data.first, data.second?.open.toString())
                    binding.moduleChart.sparkMain.adapter = chartAdapter
                } catch (ex: Exception) {
                    Toast.makeText(this@CoinActivity, "ex125: ${ex.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@CoinActivity, "Error : $errorMessage", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }
}