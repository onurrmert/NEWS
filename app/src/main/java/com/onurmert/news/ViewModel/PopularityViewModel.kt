package com.onurmert.news.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.Retrofit.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularityViewModel : ViewModel() {

    val newsModel = MutableLiveData<List<ArticlesItemModel>>()

    fun getPopularity(){
        CoroutineScope(Dispatchers.Main).launch {
            newsModel.value = NewsApi.getApiClient().getPopularityNews().body()!!.articles!!
        }
    }
}