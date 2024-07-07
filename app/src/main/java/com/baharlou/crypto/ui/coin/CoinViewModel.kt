package com.baharlou.crypto.ui.coin

import androidx.lifecycle.ViewModel
import com.baharlou.crypto.model.repository.coin.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(coinRepository: CoinRepository) : ViewModel() {
}