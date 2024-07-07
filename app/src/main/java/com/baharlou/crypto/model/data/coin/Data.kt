package com.baharlou.crypto.model.data.coin

import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("CoinInfo") var CoinInfo: CoinInfo? = CoinInfo(),
    @SerializedName("RAW") var RAW: RAW? = RAW(),
    @SerializedName("DISPLAY") var DISPLAY: DISPLAY? = DISPLAY()

)