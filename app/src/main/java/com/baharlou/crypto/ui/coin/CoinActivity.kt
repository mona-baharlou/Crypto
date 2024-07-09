package com.baharlou.crypto.ui.coin

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.baharlou.crypto.R
import com.baharlou.crypto.databinding.ActivityCoinBinding
import com.baharlou.crypto.ui.market.ABOUT_DATA
import com.baharlou.crypto.ui.market.BUNDLE_DATA
import com.baharlou.crypto.ui.market.COIN_DATA
import com.baharlou.crypto.model.ALL
import com.baharlou.crypto.model.HOUR
import com.baharlou.crypto.model.HOURS24
import com.baharlou.crypto.model.MONTH
import com.baharlou.crypto.model.MONTH3
import com.baharlou.crypto.model.TWITTER_BASE_URL
import com.baharlou.crypto.model.WEEK
import com.baharlou.crypto.model.YEAR
import com.baharlou.crypto.model.data.CoinAboutItem
import com.baharlou.crypto.model.data.coin.Data
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding
    private val viewModel: CoinViewModel by viewModels()

    private lateinit var dataCoin: Data
    private lateinit var dataAboutCoin: CoinAboutItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()

        try {
            val fromBundle = intent.getBundleExtra(BUNDLE_DATA)!!

            dataCoin = fromBundle.getParcelable<Data>(COIN_DATA)!!

            if (fromBundle.getParcelable<CoinAboutItem>(ABOUT_DATA) != null) {
                dataAboutCoin = fromBundle.getParcelable<CoinAboutItem>(ABOUT_DATA)!!
            } else {
                dataAboutCoin = CoinAboutItem()
            }
        } catch (ex: Exception) {
            binding.toolbar.toolbar.title = dataCoin.CoinInfo?.Name
        }
        initUI()
    }

    private fun observeData() {
        viewModel.chartData.observe(this) {
            setChartData()
        }
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
        binding.moduleAbout.tvTwitter.text =
            if (dataAboutCoin.coinTwitter != "no-data") "@${dataAboutCoin.coinTwitter}"
            else dataAboutCoin.coinTwitter

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
        binding.moduleStatistics.tvOpenAmount.text = dataCoin.DISPLAY.USD.OPEN24HOUR
        binding.moduleStatistics.tvTodayHigh.text = dataCoin.DISPLAY.USD.HIGH24HOUR
        binding.moduleStatistics.tvTodayLow.text = dataCoin.DISPLAY.USD.LOW24HOUR
        binding.moduleStatistics.tvTodayChange.text = dataCoin.DISPLAY.USD.CHANGE24HOUR
        binding.moduleStatistics.tvVolume.text = dataCoin.DISPLAY.USD.VOLUME24HOUR
        binding.moduleStatistics.tvVolume.text = dataCoin.DISPLAY.USD.VOLUME24HOURTO
        binding.moduleStatistics.tvtotalVolume.text = dataCoin.DISPLAY.USD.TOTALVOLUME24H
        binding.moduleStatistics.tvMarketCap.text = dataCoin.DISPLAY.USD.MKTCAP
        binding.moduleStatistics.tvSupply.text = dataCoin.DISPLAY.USD.SUPPLY
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

        binding.moduleChart.txtChartPrice.text = dataCoin.DISPLAY.USD.PRICE
        binding.moduleChart.txtChartChange1.text = " " + dataCoin.DISPLAY.USD.CHANGE24HOUR

        if (dataCoin.CoinInfo.FullName == "BUSD") {
            binding.moduleChart.txtChartChange2.text = "0%"
        } else {
            binding.moduleChart.txtChartChange2.text =
                dataCoin.RAW.USD.CHANGEPCT24HOUR.toString().substring(0, 5) + "%"
            //dataCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 5) + "%"
        }

        val change = dataCoin.DISPLAY.USD.CHANGEPCT24HOUR ?: ""
        if (!change.startsWith("-")) {

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

        } else if (change.startsWith("-")) {

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
                binding.moduleChart.txtChartPrice.text = dataCoin.DISPLAY.USD.PRICE
            } else {
                // show price this dot
                binding.moduleChart.txtChartPrice.text =
                    "$ " + (it as com.baharlou.crypto.model.data.chart.Data).close?.toString()
            }

        }
    }

    private fun requestAndShowChart(period: String) {
        viewModel.getChartData(dataCoin.CoinInfo.Name, period)

    }

    private fun setChartData() {
        if (viewModel.chartData.value != null) {
            val chartAdapter = ChartAdapter(
                viewModel.chartData.value?.first!!,
                viewModel.chartData.value?.second?.open.toString()
            )
            binding.moduleChart.sparkMain.adapter = chartAdapter
        }
    }
}