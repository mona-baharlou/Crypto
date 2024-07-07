package com.baharlou.crypto.model.data.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class Rating (

  @SerializedName("Weiss" ) var Weiss : Weiss? = Weiss()

): Parcelable