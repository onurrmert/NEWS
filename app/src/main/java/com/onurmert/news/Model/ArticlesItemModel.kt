package com.onurmert.news.Model

import com.google.gson.annotations.SerializedName

data class ArticlesItemModel(
    @SerializedName("publishedAt")
    val history: String? = "",
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("urlToImage")
    val urlToImage: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("source")
    val source: SourceModel?,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("content")
    val content: String? = ""
)