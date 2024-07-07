package com.baharlou.crypto.model.data.coin

import com.google.gson.annotations.SerializedName


data class RAW(

    @SerializedName("USD") var USD: USD? = USD()

)