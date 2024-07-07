package com.baharlou.crypto.model.data.news

import com.google.gson.annotations.SerializedName


data class SourceInfo(

    @SerializedName("name") var name: String? = null,
    @SerializedName("img") var img: String? = null,
    @SerializedName("lang") var lang: String? = null

)