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
import com.onurmert.news.ViewModel.TimeViewModel
import com.onurmert.news.databinding.FragmentTimeNewsBinding
import com.onurmert.retro4fitt.Utils.InternetControl

class TimeNewsFragment : Fragment() {

    private lateinit var binding: FragmentTimeNewsBinding

    private lateinit var viewModel: TimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTimeNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar1.toolbarText.text = "Time"

        viewModel = ViewModelProvider(requireActivity()).get(TimeViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        internetControl()
    }

    private fun internetControl(){
        if (InternetControl.connectionControl(requireContext()) == false){
            try {
                viewModel.getTimeNews()
                getTimeNews()
                swipeRefresh()
            }catch (e : java.lang.Exception){
                println("getTimeNews error: " + e.localizedMessage)
            }
        }
    }

    private fun swipeRefresh(){
        binding.swipeView.setOnRefreshListener {
            getTimeNews()
            viewModel.getTimeNews()
            binding.swipeView.isRefreshing = false
        }
    }

    private fun getTimeNews(){
        try {
            viewModel.newsModel.observe(viewLifecycleOwner, Observer {
                item->
                run{
                    println(item.get(0).source!!.name)
                    binding.recyclerView6.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerView6.adapter = AllNewsAdapter(item)
            }
            })
        }catch (e : Exception){
            println("error getTime" + e.localizedMessage)
        }
    }
}