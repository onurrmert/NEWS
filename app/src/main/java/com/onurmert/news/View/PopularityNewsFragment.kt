package com.onurmert.news.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurmert.news.Adapter.PopularityAdapter
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.ViewModel.PopularityViewModel
import com.onurmert.news.databinding.FragmentPopularityNewsBinding
import com.onurmert.retro4fitt.Utils.InternetControl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularityNewsFragment : Fragment() {

    private lateinit var binding : FragmentPopularityNewsBinding

    private lateinit var viewModel: PopularityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPopularityNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar1.toolbarText.text= "Popularity News"

        viewModel = ViewModelProvider(requireActivity()).get(PopularityViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        internetControl()
    }

    private fun internetControl(){
        if (InternetControl.connectionControl(requireContext()) == false){
            try {
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.getPopularity()
                    getPopularity1()
                    swipeRefresh()
                }
            }catch (e : Exception){
                println("getPopularity1 error: " + e.localizedMessage)
            }
        }else{
            InternetControl.internetSnackMessage(InternetControl.connectionControl(requireContext()), binding.popularityNewsView)
        }
    }

    private fun swipeRefresh(){
        binding.swipeView.setOnRefreshListener {
            getPopularity1()
            viewModel.getPopularity()
            binding.swipeView.isRefreshing = false
        }
    }

    private fun getPopularity1(){
        try {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.newsModel.observe(viewLifecycleOwner, Observer {
                        item->
                    try {
                        createRecycler(item)
                    } catch (e: Exception) {
                        println("createRecycler error: " + e.localizedMessage)
                    }
                })
            }
        } catch (e : Exception){
            println("getPopularity1 error:" + e.localizedMessage)
        }
    }

    private fun createRecycler(list: List<ArticlesItemModel>){
        binding.recyclerView5.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView5.adapter = PopularityAdapter(list)
    }
}