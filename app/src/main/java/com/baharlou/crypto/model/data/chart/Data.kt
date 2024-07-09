package com.baharlou.crypto.model.data.chart

import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("time") var time: Int? = null,
    @SerializedName("high") var high: Double? = null,
    @SerializedName("low") var low: Double? = null,
    @SerializedName("open") var open: Double? = null,
    @SerializedName("volumefrom") var volumefrom: Double? = null,
    @SerializedName("volumeto") var volumeto: Double? = null,
    @SerializedName("close") var close: Double? = null,
    @SerializedName("conversionType") var conversionType: String? = null,
    @SerializedName("conversionSymbol") var conversionSymbol: String? = null

)