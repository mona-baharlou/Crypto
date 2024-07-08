package com.baharlou.crypto.model.repository.coin

import com.baharlou.crypto.model.net.ApiService
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : CoinRepository {
}