package com.baharlou.crypto.ui.market

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharlou.crypto.model.data.CoinAboutData
import com.baharlou.crypto.model.data.CoinAboutItem
import com.baharlou.crypto.databinding.ActivityMarketBinding
import com.baharlou.crypto.model.data.coin.Data
import com.baharlou.crypto.ui.coin.CoinActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

const val COIN_DATA = "coin_data"
const val ABOUT_DATA = "about_data"
const val BUNDLE_DATA = "bundle"

@AndroidEntryPoint
class MarketActivity : AppCompatActivity(), MarketAdapter.RecyclerCallback {

    private lateinit var binding: ActivityMarketBinding

    private val viewModel: MarketViewModel by viewModels()

    lateinit var newsData: ArrayList<Pair<String, String>>
    lateinit var aboutDataMap: MutableMap<String, CoinAboutItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadMore()
        getAbout()

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
        observeData()
    }

    private fun observeData() {
        viewModel.newsList.observe(this){
            setNews()
        }

        viewModel.coinList.observe(this){
            setCoins()
        }
    }

    private fun loadMore() {
        binding.moduleWatchlist.btnShowMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.livecoinwatch.com/"))
            startActivity(intent)
        }
    }

    private fun initUI() {
        viewModel.getNews()
        viewModel.getCoins()
    }

    private fun getAbout() {
        val fileInString = applicationContext.assets
            .open("currencyinfo.json")
            .bufferedReader()
            .use {
                it.readText()
            }


        aboutDataMap = mutableMapOf<String, CoinAboutItem>()
        val gson = Gson()
        val dataAbout = gson.fromJson(fileInString, CoinAboutData::class.java)

        dataAbout.forEach {
            aboutDataMap[it.currencyName] = CoinAboutItem(
                coinWebsite = it.info.web,
                coinGithub = it.info.github,
                coinTwitter = it.info.twt,
                coinReddit = it.info.reddit,
                coinDesc = it.info.desc
            )
        }


    }

    private fun setCoins() {
        if (viewModel.coinList.value!!.isNotEmpty()) {
            showData(cleanDataFromServer(viewModel.coinList.value!!))
        }
    }

    private fun cleanDataFromServer(data: List<Data>): List<Data> {
        val newData = mutableListOf<Data>()
        data.forEach {
            if (it.RAW != null || it.DISPLAY != null) {
                newData.add(it)
            }
        }

        return newData
    }

    private fun showData(data: List<Data>) {
        val marketAdapter = MarketAdapter(ArrayList(data), this)
        binding.moduleWatchlist.recyclerMain.adapter = marketAdapter
        binding.moduleWatchlist.recyclerMain.layoutManager = LinearLayoutManager(this)
    }

    private fun setNews() {
        try {
            if (viewModel.newsList.value!!.isNotEmpty()) {
                newsData = viewModel.newsList.value!!
                refreshNews()
            }
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

    override fun onCoinItemClicked(dataCoin: Data) {
        val intent = Intent(this, CoinActivity::class.java)

        val bundle = Bundle()
        bundle.putParcelable(COIN_DATA, dataCoin)
        bundle.putParcelable(ABOUT_DATA, aboutDataMap[dataCoin.CoinInfo!!.Name]!!)
        intent.putExtra(BUNDLE_DATA, bundle)
        startActivity(intent)
    }
}