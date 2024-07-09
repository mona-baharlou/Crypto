package com.baharlou.crypto.model.data.chart

import com.google.gson.annotations.SerializedName


data class ChartResponse(
    @SerializedName("Response") var Response: String? = null,
    @SerializedName("Type") var Type: Int? = null,
    @SerializedName("Aggregated") var Aggregated: Boolean? = null,
    @SerializedName("TimeTo") var TimeTo: Int? = null,
    @SerializedName("TimeFrom") var TimeFrom: Int? = null,
    @SerializedName("FirstValueInArray") var FirstValueInArray: Boolean? = null,
    @SerializedName("ConversionType") var ConversionType: ConversionType? = ConversionType(),
    @SerializedName("Data") var Data: ArrayList<Data> = arrayListOf(),
    @SerializedName("RateLimit") var RateLimit: RateLimit? = RateLimit(),
    @SerializedName("HasWarning") var HasWarning: Boolean? = null

)