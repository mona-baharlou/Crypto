package com.baharlou.crypto.model.data.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class USD(

    @SerializedName("FROMSYMBOL") var FROMSYMBOL: String? = null,
    @SerializedName("TOSYMBOL") var TOSYMBOL: String? = null,
    @SerializedName("MARKET") var MARKET: String? = null,
    @SerializedName("LASTMARKET") var LASTMARKET: String? = null,
    @SerializedName("TOPTIERVOLUME24HOUR") var TOPTIERVOLUME24HOUR: String? = null,
    @SerializedName("TOPTIERVOLUME24HOURTO") var TOPTIERVOLUME24HOURTO: String? = null,
    @SerializedName("LASTTRADEID") var LASTTRADEID: String? = null,
    @SerializedName("PRICE") var PRICE: String? = null,
    @SerializedName("LASTUPDATE") var LASTUPDATE: String? = null,
    @SerializedName("LASTVOLUME") var LASTVOLUME: String? = null,
    @SerializedName("LASTVOLUMETO") var LASTVOLUMETO: String? = null,
    @SerializedName("VOLUMEHOUR") var VOLUMEHOUR: String? = null,
    @SerializedName("VOLUMEHOURTO") var VOLUMEHOURTO: String? = null,
    @SerializedName("OPENHOUR") var OPENHOUR: String? = null,
    @SerializedName("HIGHHOUR") var HIGHHOUR: String? = null,
    @SerializedName("LOWHOUR") var LOWHOUR: String? = null,
    @SerializedName("VOLUMEDAY") var VOLUMEDAY: String? = null,
    @SerializedName("VOLUMEDAYTO") var VOLUMEDAYTO: String? = null,
    @SerializedName("OPENDAY") var OPENDAY: String? = null,
    @SerializedName("HIGHDAY") var HIGHDAY: String? = null,
    @SerializedName("LOWDAY") var LOWDAY: String? = null,
    @SerializedName("VOLUME24HOUR") var VOLUME24HOUR: String? = null,
    @SerializedName("VOLUME24HOURTO") var VOLUME24HOURTO: String? = null,
    @SerializedName("OPEN24HOUR") var OPEN24HOUR: String? = null,
    @SerializedName("HIGH24HOUR") var HIGH24HOUR: String? = null,
    @SerializedName("LOW24HOUR") var LOW24HOUR: String? = null,
    @SerializedName("CHANGE24HOUR") var CHANGE24HOUR: String? = null,
    @SerializedName("CHANGEPCT24HOUR") var CHANGEPCT24HOUR: String? = null,
    @SerializedName("CHANGEDAY") var CHANGEDAY: String? = null,
    @SerializedName("CHANGEPCTDAY") var CHANGEPCTDAY: String? = null,
    @SerializedName("CHANGEHOUR") var CHANGEHOUR: String? = null,
    @SerializedName("CHANGEPCTHOUR") var CHANGEPCTHOUR: String? = null,
    @SerializedName("CONVERSIONTYPE") var CONVERSIONTYPE: String? = null,
    @SerializedName("CONVERSIONSYMBOL") var CONVERSIONSYMBOL: String? = null,
    @SerializedName("CONVERSIONLASTUPDATE") var CONVERSIONLASTUPDATE: String? = null,
    @SerializedName("SUPPLY") var SUPPLY: String? = null,
    @SerializedName("MKTCAP") var MKTCAP: String? = null,
    @SerializedName("MKTCAPPENALTY") var MKTCAPPENALTY: String? = null,
    @SerializedName("CIRCULATINGSUPPLY") var CIRCULATINGSUPPLY: String? = null,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP") var CIRCULATINGSUPPLYMKTCAP: String? = null,
    @SerializedName("TOTALVOLUME24H") var TOTALVOLUME24H: String? = null,
    @SerializedName("TOTALVOLUME24HTO") var TOTALVOLUME24HTO: String? = null,
    @SerializedName("TOTALTOPTIERVOLUME24H") var TOTALTOPTIERVOLUME24H: String? = null,
    @SerializedName("TOTALTOPTIERVOLUME24HTO") var TOTALTOPTIERVOLUME24HTO: String? = null,
    @SerializedName("IMAGEURL") var IMAGEURL: String? = null

): Parcelable