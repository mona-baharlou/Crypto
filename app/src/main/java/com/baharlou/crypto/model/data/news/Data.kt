package com.baharlou.crypto.model.data.news

import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("id") var id: String? = null,
    @SerializedName("guid") var guid: String? = null,
    @SerializedName("published_on") var publishedOn: Int? = null,
    @SerializedName("imageurl") var imageurl: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("body") var body: String? = null,
    @SerializedName("tags") var tags: String? = null,
    @SerializedName("lang") var lang: String? = null,
    @SerializedName("upvotes") var upvotes: String? = null,
    @SerializedName("downvotes") var downvotes: String? = null,
    @SerializedName("categories") var categories: String? = null,
    @SerializedName("source_info") var sourceInfo: SourceInfo? = SourceInfo(),
    @SerializedName("source") var source: String? = null

)