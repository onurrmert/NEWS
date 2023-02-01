package com.onurmert.news.View

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurmert.news.Adapter.EverythingNewsAdapter
import com.onurmert.news.Model.ArticlesItemModel
import com.onurmert.news.R
import com.onurmert.news.Utils.ProgresDialog1
import com.onurmert.news.ViewModel.EverythingNewsViewModel
import com.onurmert.news.databinding.FragmentEverythingnewsBinding
import com.onurmert.retro4fitt.Utils.InternetControl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EverythingNewsFragment : Fragment() {

    private lateinit var binding: FragmentEverythingnewsBinding

    private lateinit var viewModel: EverythingNewsViewModel

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEverythingnewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.drawerEverythingNews.closeDrawers()

        viewModel = ViewModelProvider(requireActivity()).get(EverythingNewsViewModel::class.java)

        internetControl()

        try {
            drawerMenu()
        }catch (e : Exception){
            println("drawer menu error: " + e.localizedMessage)
        }

        binding.imageViewMenu.setOnClickListener {
            binding.drawerEverythingNews.open()
        }
    }

    override fun onResume() {
        super.onResume()

        binding.drawerEverythingNews.closeDrawers()

        try {
            drawerMenuItem()
        }catch (e : Exception){
            println("drawerMenuItem error: " + e.localizedMessage)
        }
    }

    private fun drawerMenu(){

        toggle = ActionBarDrawerToggle(requireActivity(), binding.drawerEverythingNews, R.string.nav_open, R.string.nav_close)

        binding.drawerEverythingNews.setDrawerListener(toggle)

        toggle.syncState()
    }

    private fun drawerMenuItem(){

        binding.navigationDrawer.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.CnnMenu->{
                    goNewsPage(EverythingNewsFragmentDirections.actionCurrentFragmentToCnnNewsFragment())
                }
                R.id.BBCMenu->{
                    goNewsPage(EverythingNewsFragmentDirections.actionCurrentFragmentToBbcNewsFragment())
                }
                R.id.usaTodayMenu->{
                    goNewsPage(EverythingNewsFragmentDirections.actionCurrentFragmentToUsaTodayNewsFragment())
                }
                R.id.popularityMenu->{
                    goNewsPage(EverythingNewsFragmentDirections.actionCurrentFragmentToPopularityNewsFragment())
                }
                R.id.timeNewsMenu->{
                    goNewsPage(EverythingNewsFragmentDirections.actionCurrentFragmentToTimeNewsFragment())
                }
                R.id.businessNewsMenu->{
                    goNewsPage(EverythingNewsFragmentDirections.actionCurrentFragmentToBusinessNewsFragment())
                }
            }
            true
        }
    }

    private fun internetControl(){

        if (InternetControl.connectionControl(requireContext()) == false){
            try {
                viewModel.getEverything()
                getAll()
                swipeRefresh()
            }catch (e : Exception){
                println("EverythingNewsFragment error1: " + e.localizedMessage!!)
            }
        }else{
            InternetControl.internetSnackMessage(InternetControl.connectionControl(requireContext()), binding.drawerEverythingNews)
        }
    }

    private fun swipeRefresh(){
        binding.swipeView.setOnRefreshListener {
            getAll()
            viewModel.getEverything()
            binding.swipeView.isRefreshing = false
        }
    }

    private fun getAll(){

        viewModel.everything.observe(viewLifecycleOwner, Observer {
            item->
            run {
                try {
                    createRecycler(item)
                }catch ( e : Exception){
                    println("error createRecycler: " + e.localizedMessage!!)
                }
            }
        })
    }

    private fun createRecycler(list : List<ArticlesItemModel>){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = EverythingNewsAdapter(list)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goNewsPage(navDirections: NavDirections){
        CoroutineScope(Dispatchers.Main).launch {
            ProgresDialog1.dialog(requireContext(), "Loading...")
            binding.drawerEverythingNews.closeDrawers()
            Navigation.findNavController(requireView()).navigate(navDirections)
        }
    }
}