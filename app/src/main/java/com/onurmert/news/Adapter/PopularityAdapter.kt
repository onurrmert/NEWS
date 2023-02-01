package com.onurmert.news.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.R
import com.onurmert.news.Utils.ProgresDialog1
import com.onurmert.news.View.PopularityNewsFragmentDirections
import com.onurmert.news.databinding.RecyclersRowsBinding

class PopularityAdapter(val list: List<ArticlesItemModel>) : RecyclerView.Adapter<PopularityAdapter.PopularityViewHolder>(){

    class PopularityViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = RecyclersRowsBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularityViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclers_rows, parent, false)

        return PopularityViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PopularityViewHolder, position: Int) {

        if (list.get(position).urlToImage != null && list.get(position).urlToImage != ""){

            Glide.with(holder.binding.imageView2.context)
                .load(list.get(position).urlToImage)
                .error(R.drawable.error_img)
                .into(holder.binding.imageView2)

            holder.binding.titleText.setText(list.get(position).title ?: "")

            holder.binding.historyText.setText(list.get(position).history ?: "")

            val direction = PopularityNewsFragmentDirections
                .actionPopularityNewsFragmentToEverythingDetailFragment(
                    list.get(position).author ?: "",
                    list.get(position).title ?: "",
                    list.get(position).urlToImage ?: "",
                    list.get(position).description ?: "",
                    list.get(position).history ?: "",
                    list.get(position).url ?: ""
                )

            holder.binding.rowView.setOnClickListener {
                ProgresDialog1.dialog(holder.itemView.context, "Loading...")
                Navigation.findNavController(it).navigate(direction)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}