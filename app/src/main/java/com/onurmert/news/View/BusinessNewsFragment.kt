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
import com.onurmert.news.ViewModel.BusinessViewModel
import com.onurmert.news.databinding.FragmentBusinessNewsBinding
import com.onurmert.retro4fitt.Utils.InternetControl

class BusinessNewsFragment : Fragment() {

    private lateinit var binding : FragmentBusinessNewsBinding

    private lateinit var viewModel: BusinessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBusinessNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(BusinessViewModel::class.java)

        binding.toolbar1.toolbarText.text = "BusinessNews"
    }

    override fun onResume() {
        super.onResume()
        internetControl()
    }

    private fun internetControl(){
        if (InternetControl.connectionControl(requireContext()) == false){
            try {
                viewModel.getBusiness()
                getBusiness()
                swipeRefresh()
            }catch (e :java.lang.Exception){
                println("getBusiness error: " + e.localizedMessage)
            }
        }else{
            InternetControl.internetSnackMessage(InternetControl.connectionControl(requireContext()), binding.businessView)
        }
    }

    private fun swipeRefresh(){
        binding.swipeView.setOnRefreshListener {
            getBusiness()
            viewModel.getBusiness()
            binding.swipeView.isRefreshing = false
        }
    }

    private fun getBusiness(){
        try {
            viewModel.newsModel.observe(viewLifecycleOwner, Observer {
                item->
                kotlin.run {
                    createRecycler(item)
                }
            })
        }catch (e : java.lang.Exception){
            println("viewModel error: " + e.localizedMessage)
        }
    }

    private fun createRecycler(list: List<ArticlesItemModel>){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = AllNewsAdapter(list)
    }
}