package com.onurmert.news.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurmert.news.Adapter.AllNewsAdapter
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.ViewModel.UsaTodayViewModel
import com.onurmert.news.databinding.FragmentUsaTodayNewsBinding
import com.onurmert.retro4fitt.Utils.InternetControl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsaTodayNewsFragment : Fragment() {

    private lateinit var binding: FragmentUsaTodayNewsBinding

    private lateinit var viewModel: UsaTodayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUsaTodayNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar1.toolbarText.text = "UsaToday"

        viewModel = ViewModelProvider(requireActivity()).get(UsaTodayViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        internetControl()
    }

    private fun internetControl(){
        if (InternetControl.connectionControl(requireContext()) == false){
            try {
                viewModel.getUsaToday()
                getUsaToday1()
                swipeRefresh()
            }catch (e : Exception){
                println("getUsaToday error: " + e.localizedMessage)
            }
        }else{
            InternetControl.internetSnackMessage(true, binding.usaTodayView)
        }
    }

    private fun swipeRefresh(){
        binding.swipeView.setOnRefreshListener {
            getUsaToday1()
            viewModel.getUsaToday()
            binding.swipeView.isRefreshing = false
        }
    }

    private fun getUsaToday1(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.newsModel.observe(viewLifecycleOwner, Observer {
                item->
                run{
                    try {
                        createRecycler(item)
                    }catch (e : Exception){
                        println("creataRecycler error: " + e.localizedMessage)
                    }
                }
            })
        }
    }

    private fun createRecycler(list: List<ArticlesItemModel>){
        binding.recyclerView4.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView4.adapter = AllNewsAdapter(list)
    }
}