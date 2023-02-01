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
import com.onurmert.news.ViewModel.BbcViewModel
import com.onurmert.news.databinding.FragmentBbcNewsBinding
import com.onurmert.retro4fitt.Utils.InternetControl

class BbcNewsFragment : Fragment() {

    private lateinit var binding : FragmentBbcNewsBinding

    private lateinit var viewModel : BbcViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBbcNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar1.toolbarText.text = "BBC"

        viewModel = ViewModelProvider(requireActivity()).get(BbcViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        internetControl()
    }

    private fun internetControl(){
        if (InternetControl.connectionControl(requireContext()) == false){
            try {
                viewModel.getBbcNews()
                getBBCNews()
                swipeRefresh()
            }catch (e :java.lang.Exception){
                println("getNews error: " + e.localizedMessage)
            }
        }else{
            InternetControl.internetSnackMessage(InternetControl.connectionControl(requireContext()), binding.bbcView)
        }
    }

    private fun swipeRefresh(){
        binding.swipeView.setOnRefreshListener {
            getBBCNews()
            viewModel.getBbcNews()
            binding.swipeView.isRefreshing = false
        }
    }

    private fun getBBCNews(){
        viewModel.newsModel.observe(viewLifecycleOwner, Observer {
            item->
            try {
                createRecycler(item)
            }catch (e : java.lang.Exception){
                println("createRecycler error: " + e.localizedMessage)
            }
        })
    }

    private fun createRecycler(list: List<ArticlesItemModel>){
        binding.recyclerView3.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView3.adapter = AllNewsAdapter(list)
    }
}