package com.baharlou.crypto.model.data.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class Weiss(

    @SerializedName("Rating") var Rating: String? = null,
   /* @SerializedName("TechnologyAdoptionRating") var TechnologyAdoptionRating: String? = null,
    @SerializedName("MarketPerformanceRating") var MarketPerformanceRating: String? = null
*/
):Parcelable