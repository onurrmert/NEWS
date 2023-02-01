package com.onurmert.news.Model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<ArticlesItemModel>?,
    @SerializedName("status")
    val status: String = ""
)