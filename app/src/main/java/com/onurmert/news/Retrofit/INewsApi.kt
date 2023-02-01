package com.onurmert.news.Retrofit

import com.onurmert.news.Model.NewsModel
import com.onurmert.news.Utils.Constans1
import retrofit2.Response
import retrofit2.http.GET

interface INewsApi {

    @GET("top-headlines?country=us&apiKey=${Constans1.API_KEY}")
    suspend fun getEverythingNews() : Response<NewsModel>

    @GET("everything?q=Apple&from=2022-12-02&sortBy=popularity&apiKey=${Constans1.API_KEY}")
    suspend fun getPopularityNews() : Response<NewsModel>

    @GET("top-headlines?sources=bbc-news&apiKey=${Constans1.API_KEY}")
    suspend fun getBBCNews() : Response<NewsModel>

    @GET("top-headlines?sources=cnn&apiKey=${Constans1.API_KEY}")
    suspend fun getCNNews() : Response<NewsModel>

    @GET("top-headlines?sources=usa-today&apiKey=${Constans1.API_KEY}")
    suspend fun getUsaTodayNews() : Response<NewsModel>

    @GET("top-headlines?sources=business-insider&apiKey=${Constans1.API_KEY}")
    suspend fun getBusinessNews() : Response<NewsModel>

    @GET("top-headlines?sources=time&apiKey=${Constans1.API_KEY}")
    suspend fun getTimeNews() : Response<NewsModel>
}