package com.baharlou.crypto.features

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharlou.crypto.apiManager.ApiManager
import com.baharlou.crypto.databinding.ActivityMarketBinding

class MarketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketBinding
    var apiManager = ApiManager()
    lateinit var newsData: ArrayList<Pair<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initUI()

    }

    private fun initUI() {

        getNews()

        getCoins()

    }

    private fun getCoins() {

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
}