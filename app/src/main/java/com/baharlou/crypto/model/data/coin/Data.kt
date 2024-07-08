package com.baharlou.crypto.model.data.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(

    @SerializedName("CoinInfo") var CoinInfo: CoinInfo ,
    @SerializedName("RAW") var RAW: RAW ,
    @SerializedName("DISPLAY") var DISPLAY: DISPLAY

) : Parcelable