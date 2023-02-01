package com.onurmert.news.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.Retrofit.NewsApi
import kotlinx.coroutines.launch

class CnnViewModel : ViewModel() {

    val newsModel = MutableLiveData<List<ArticlesItemModel>>()

    fun getCnnNews(){
        viewModelScope.launch {
            newsModel.value = NewsApi.getApiClient().getCNNews().body()!!.articles!!
        }
    }
}