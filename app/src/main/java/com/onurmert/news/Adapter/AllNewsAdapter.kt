package com.onurmert.news.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.R
import com.onurmert.news.Utils.NavDirect1
import com.onurmert.news.Utils.ProgresDialog1
import com.onurmert.news.databinding.RecyclersRowsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllNewsAdapter (val list : List<ArticlesItemModel>) : RecyclerView.Adapter<AllNewsAdapter.AllViewHolder>(){

    class AllViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding = RecyclersRowsBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclers_rows, parent, false)

        return AllViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AllViewHolder, position: Int) {

        Glide.with(holder.binding.imageView2.context)
            .load(list.get(position).urlToImage)
            .error(R.drawable.error_img)
            .into(holder.binding.imageView2)

        holder.binding.titleText.setText(list.get(position).title ?: "")

        holder.binding.historyText.setText(list.get(position).history ?: "")

        holder.binding.rowView.setOnClickListener {

            if (list.get(position).source!!.name == "USA Today"){
                try {
                    NavDirect1.goUsaToday(it!!, list.get(position))
                }catch (e : Exception){
                    println("goUsaToday error" + e.localizedMessage)
                }
            }

            if (list.get(position).source!!.name == "CNN"){
                try {
                    NavDirect1.goCnn(it!!, list.get(position))
                }catch (e : Exception){
                    println("goCnn error" + e.localizedMessage)
                }
            }

            if (list.get(position).source!!.name == "BBC News"){
                try {
                    NavDirect1.goBbc(it!!, list.get(position))
                }catch (e : Exception){
                    println("goBbc error" + e.localizedMessage)
                }
            }

            if (list.get(position).source!!.name == "Time"){
                try {
                    NavDirect1.goTimeNews(it!!, list.get(position))
                }catch (e : Exception){
                    println("goBbc error" + e.localizedMessage)
                }
            }
            if(list.get(position).source!!.name == "Business Insider"){
                try {
                    NavDirect1.goTBusiness(it, list.get(position))
                }catch (e : Exception){
                    println("goTBusiness error: " + e.localizedMessage)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}