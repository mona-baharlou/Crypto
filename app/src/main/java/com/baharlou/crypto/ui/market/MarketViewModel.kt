package com.baharlou.crypto.ui.market

import androidx.lifecycle.ViewModel
import com.baharlou.crypto.model.net.ApiService
import com.baharlou.crypto.model.repository.market.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(marketRepository: MarketRepository) : ViewModel(){
}