package com.baharlou.crypto.model.data.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinResponse(

    @SerializedName("Message") var Message: String? = null,
    @SerializedName("Type") var Type: Int? = null,
    /*@SerializedName("MetaData") var MetaData: MetaData? = MetaData(),
    @SerializedName("SponsoredData") var SponsoredData: ArrayList<String> = arrayListOf(),*/
    @SerializedName("Data") var Data: ArrayList<Data> = arrayListOf(),
    //@SerializedName("RateLimit") var RateLimit: RateLimit? = RateLimit(),
    @SerializedName("HasWarning") var HasWarning: Boolean? = null

):Parcelable