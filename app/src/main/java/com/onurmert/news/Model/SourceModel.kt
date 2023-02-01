package com.onurmert.news.Model

import com.google.gson.annotations.SerializedName

data class SourceModel(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: String = ""
)