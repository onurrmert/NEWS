package com.onurmert.news.Retrofit

import com.google.gson.GsonBuilder
import com.onurmert.news.Utils.Constans1
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NewsApi {

    private fun getRetrofit() : Retrofit{

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val BASE_URL = "http://www.omdbapi.com/"

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constans1.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    companion object{
        fun getApiClient() : INewsApi{
            return NewsApi().getRetrofit().create(INewsApi::class.java)
        }
    }
}