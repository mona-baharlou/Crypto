package com.baharlou.crypto.model.data.news

import com.google.gson.annotations.SerializedName


data class NewsResponse(
    @SerializedName("Type") var Type: Int? = null,
    @SerializedName("Message") var Message: String? = null,
    @SerializedName("Promoted") var Promoted: ArrayList<String> = arrayListOf(),
    @SerializedName("Data") var Data: ArrayList<Data> = arrayListOf(),
    @SerializedName("RateLimit") var RateLimit: RateLimit? = RateLimit(),
    @SerializedName("HasWarning") var HasWarning: Boolean? = null

)