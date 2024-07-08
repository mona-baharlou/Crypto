package com.baharlou.crypto.ui.market

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharlou.crypto.model.data.coin.Data
import com.baharlou.crypto.model.repository.market.MarketRepositoryImpl
import com.baharlou.crypto.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    val marketRepository: MarketRepositoryImpl
) : ViewModel() {


    var newsList = MutableLiveData<ArrayList<Pair<String, String>>>()
    var coinList = MutableLiveData<List<Data>>()

    init {
        getNews()
        getCoins()
    }


    fun getNews() {
        viewModelScope.launch(coroutineExceptionHandler) {

            val news = marketRepository.getNews()
            newsList.postValue(news)
        }

    }

    fun getCoins() {
        viewModelScope.launch(coroutineExceptionHandler) {
            coinList.postValue(marketRepository.getCoins())
        }
    }

}