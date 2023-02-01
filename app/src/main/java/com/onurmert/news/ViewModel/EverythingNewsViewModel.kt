package com.onurmert.news.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.Retrofit.NewsApi
import kotlinx.coroutines.launch

class EverythingNewsViewModel : ViewModel() {

    val everything = MutableLiveData<List<ArticlesItemModel>>()

    fun getEverything(){
        viewModelScope.launch {
            everything.value = NewsApi.getApiClient().getEverythingNews().body()!!.articles!!
        }
    }
}