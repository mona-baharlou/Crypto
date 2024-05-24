package com.baharlou.crypto.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharlou.crypto.apiManager.ApiManager
import com.baharlou.crypto.databinding.ActivityMarketBinding

class MarketActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMarketBinding
    lateinit var apiManager: ApiManager

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



    }
}