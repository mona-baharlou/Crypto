package com.baharlou.crypto.features.market

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharlou.crypto.apiManager.ApiManager
import com.baharlou.crypto.apiManager.model.CoinAboutData
import com.baharlou.crypto.apiManager.model.CoinAboutItem
import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.databinding.ActivityMarketBinding
import com.baharlou.crypto.features.CoinActivity
import com.google.gson.Gson

class MarketActivity : AppCompatActivity(), MarketAdapter.RecyclerCallback {

    private lateinit var binding: ActivityMarketBinding
    var apiManager = ApiManager()
    lateinit var newsData: ArrayList<Pair<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadMore()

        binding.swipeRefreshMain.setOnRefreshListener {
            initUI()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefreshMain.isRefreshing = false
            }, 1500)
        }
    }

    override fun onResume() {
        super.onResume()
        initUI()
    }

    private fun loadMore() {
        binding.moduleWatchlist.btnShowMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.livecoinwatch.com/"))
            startActivity(intent)
        }
    }

    private fun initUI() {
        getNews()
        getCoins()
        getAbout()
    }

    private fun getAbout() {
        val fileInString = applicationContext.assets
            .open("currencyinfo.json")
            .bufferedReader()
            .use {
                it.readText()
            }


        val aboutDataMap = mutableMapOf<String, CoinAboutItem>()
        val gson = Gson()
        val dataAbout = gson.fromJson(fileInString, CoinAboutData::class.java)
        dataAbout.forEach {

        }
    }

    private fun getCoins() {

        apiManager.getCoinList(object : ApiManager.ApiCallback<List<CoinsData.Data>> {
            override fun onSuccess(data: List<CoinsData.Data>) {
                showData(data)

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@MarketActivity, "Error : $errorMessage", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

    private fun showData(data: List<CoinsData.Data>) {

        val marketAdapter = MarketAdapter(ArrayList(data), this)
        binding.moduleWatchlist.recyclerMain.adapter = marketAdapter
        binding.moduleWatchlist.recyclerMain.layoutManager = LinearLayoutManager(this)


    }

    private fun getNews() {

        try {


            apiManager.getNews(object : ApiManager.ApiCallback<ArrayList<Pair<String, String>>> {
                override fun onSuccess(data: ArrayList<Pair<String, String>>) {

                    newsData = data
                    refreshNews()
                }

                override fun onError(errorMessage: String) {
                    Toast.makeText(this@MarketActivity, "Error : $errorMessage", Toast.LENGTH_SHORT)
                        .show()
                }


            })

        } catch (ex: Exception) {
            Toast.makeText(this@MarketActivity, "Exception : ${ex.message}", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun refreshNews() {

        val randomNews = (0..49).random()
        binding.moduleNews.txtNews.text = newsData[randomNews].first
        binding.moduleNews.imgNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsData[randomNews].second))
            startActivity(intent)
        }

        binding.moduleNews.txtNews.setOnClickListener {
            refreshNews()
        }


    }

    override fun onCoinItemClicked(dataCoin: CoinsData.Data) {
        val intent = Intent(this, CoinActivity::class.java)
        intent.putExtra("dataToSend", dataCoin)
        startActivity(intent)
    }
}