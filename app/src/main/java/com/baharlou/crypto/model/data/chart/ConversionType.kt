package com.baharlou.crypto.model.data.chart

import com.google.gson.annotations.SerializedName


data class ConversionType(

    @SerializedName("type") var type: String? = null,
    @SerializedName("conversionSymbol") var conversionSymbol: String? = null

)