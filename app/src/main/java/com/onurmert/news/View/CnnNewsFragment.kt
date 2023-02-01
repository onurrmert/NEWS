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
import com.onurmert.news.ViewModel.CnnViewModel
import com.onurmert.news.databinding.FragmentCnnNewsBinding
import com.onurmert.retro4fitt.Utils.InternetControl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CnnNewsFragment : Fragment(){

    private lateinit var binding: FragmentCnnNewsBinding

    private lateinit var viewModel: CnnViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCnnNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar1.toolbarText.text = "CNN"

        viewModel = ViewModelProvider(requireActivity()).get(CnnViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        internetControl()
    }
    private fun internetControl(){
        if (InternetControl.connectionControl(requireContext()) == false){
            try {
                viewModel.getCnnNews()
                getCnn()
                swipeRefresh()
            }catch (e : Exception){
                println("getCnn error: " + e.localizedMessage)
            }
        }else{
            InternetControl.internetSnackMessage(InternetControl.connectionControl(requireContext()), binding.cnnNewsView)
        }
    }

    private fun swipeRefresh(){
        binding.swipeView.setOnRefreshListener {
            getCnn()
            viewModel.getCnnNews()
            binding.swipeView.isRefreshing = false
        }
    }

    private fun getCnn(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.newsModel.observe(viewLifecycleOwner, Observer {
                item->
                kotlin.run {
                    createRecycler(item)
                }
            })
        }
    }

    private fun createRecycler(list : List<ArticlesItemModel>){
        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView2.adapter = AllNewsAdapter(list)
    }
}