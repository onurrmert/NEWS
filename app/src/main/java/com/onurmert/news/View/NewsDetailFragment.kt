package com.onurmert.news.View

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.onurmert.news.R
import com.onurmert.news.databinding.FragmentNewsDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDetailFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar1.toolbarText.text = "NewsDetail"
        try {
            CoroutineScope(Dispatchers.Main).launch {
                init()
            }
        }catch (e : Exception){
            println("init error: " + e.localizedMessage)
        }
    }

    private fun init(){

        if (!getArgs().urlToImage.equals("")){
            Glide.with(binding.detailImageView.context)
                .load(getArgs().urlToImage)
                .error(R.drawable.error_img)
                .into(binding.detailImageView)
        }

        if (!getArgs().author.equals("")){
            binding.authorText.setText(getArgs().author.trim())
        }else{
            binding.authorText.text = ""
        }

        if (!getArgs().description.equals("")){
            binding.contentText.text = getArgs().description.trim()
        }else{
            binding.contentText.text = ""
        }

        if (!getArgs().title.equals("")){
            binding.titleText.text = getArgs().title.trim()
        }else{
            binding.titleText.text = ""
        }

        binding.button.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY,getArgs().url.trim())
            startActivity(intent)
        }
    }

    private fun getArgs() : NewsDetailFragmentArgs{
        val bundle = arguments
        return NewsDetailFragmentArgs.fromBundle(bundle!!)
    }
}