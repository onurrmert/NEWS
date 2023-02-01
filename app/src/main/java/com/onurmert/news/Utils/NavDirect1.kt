package com.onurmert.news.Utils

import android.view.View
import androidx.navigation.Navigation
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.View.*

class NavDirect1 {

    companion object{

        fun goCnn(view : View, articlesItemModel: ArticlesItemModel){

            val direction = CnnNewsFragmentDirections
                .actionCnnNewsFragmentToEverythingDetailFragment(
                    articlesItemModel.author ?: "",
                    articlesItemModel.title ?: "",
                    articlesItemModel.urlToImage ?: "",
                    articlesItemModel.description ?: "",
                    articlesItemModel.history ?: "",
                    articlesItemModel.url ?: ""
                )
            Navigation.findNavController(view).navigate(direction)
        }

        fun goBbc(view : View, articlesItemModel: ArticlesItemModel){

            val direction = BbcNewsFragmentDirections
                .actionBbcNewsFragmentToEverythingDetailFragment(
                    articlesItemModel.author ?: "",
                    articlesItemModel.title ?: "",
                    articlesItemModel.urlToImage ?: "",
                    articlesItemModel.description ?: "",
                    articlesItemModel.history ?: "",
                    articlesItemModel.url ?: ""
                )
            Navigation.findNavController(view).navigate(direction)
        }

        fun goUsaToday(view : View, articlesItemModel: ArticlesItemModel){

            val direction = UsaTodayNewsFragmentDirections
                .actionUsaTodayNewsFragmentToEverythingDetailFragment(
                    articlesItemModel.author ?: "",
                    articlesItemModel.title ?: "",
                    articlesItemModel.urlToImage ?: "",
                    articlesItemModel.description ?: "",
                    articlesItemModel.history ?: "",
                    articlesItemModel.url ?: ""
                )
            Navigation.findNavController(view).navigate(direction)
        }

        fun goTimeNews(view : View, articlesItemModel: ArticlesItemModel){

            val direction = TimeNewsFragmentDirections
                .actionTimeNewsFragmentToEverythingDetailFragment(
                    articlesItemModel.author ?: "",
                    articlesItemModel.title ?: "",
                    articlesItemModel.urlToImage ?: "",
                    articlesItemModel.description ?: "",
                    articlesItemModel.history ?: "",
                    articlesItemModel.url ?: ""
                )
            Navigation.findNavController(view).navigate(direction)
        }

        fun goTBusiness(view : View, articlesItemModel: ArticlesItemModel){

            val direction = BusinessNewsFragmentDirections
                .actionBusinessNewsFragmentToEverythingDetailFragment(
                    articlesItemModel.author ?: "",
                    articlesItemModel.title ?: "",
                    articlesItemModel.urlToImage ?: "",
                    articlesItemModel.description ?: "",
                    articlesItemModel.history ?: "",
                    articlesItemModel.url ?: ""
                )
            Navigation.findNavController(view).navigate(direction)
        }
    }
}