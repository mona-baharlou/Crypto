package com.baharlou.crypto.ui.coin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharlou.crypto.model.repository.coin.CoinRepository
import com.baharlou.crypto.model.repository.coin.CoinRepositoryImpl
import com.baharlou.crypto.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(val coinRepository: CoinRepositoryImpl) : ViewModel() {

    var chartData = MutableLiveData<Pair<List<com.baharlou.crypto.model.data.chart.Data>, com.baharlou.crypto.model.data.chart.Data?>>()

    fun getChartData(symbol: String, period: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val data  = coinRepository.getChartData(symbol, period)
            chartData.postValue(data)
        }
    }

}