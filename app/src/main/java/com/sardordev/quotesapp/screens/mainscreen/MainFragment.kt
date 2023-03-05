package com.sardordev.quotesapp.screens.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sardordev.quotesapp.R
import com.sardordev.quotesapp.data.model.AllTopicsResult
import com.sardordev.quotesapp.data.model.AloneBody
import com.sardordev.quotesapp.data.model.EmptyBody
import com.sardordev.quotesapp.databinding.FragmentMainBinding
import com.sardordev.quotesapp.`object`.GetObjject
import com.sardordev.quotesapp.screens.mainscreen.adapter.QuotesAdapter
import com.sardordev.quotesapp.utils.ClickItemListener
import com.sardordev.quotesapp.utils.UiEvent
import com.sardordev.quotesapp.veiwmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment(), ClickItemListener {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()
    private val quotesAdapter = QuotesAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllTopics(EmptyBody())




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getAllTopics()
        initRecyclerView()
        initDrawer()


        return binding.root
    }

    /*
     get All topics
     {
        "id": 27,
        "name": "Fitness"
    }
     */
    private fun getAllTopics() {
        lifecycleScope.launchWhenCreated {
            viewModel.allobserver.collectLatest {
                when (it) {
                    UiEvent.Empty -> Unit
                    is UiEvent.Error -> {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                    UiEvent.Loading -> {
                        binding.progressbar.isVisible = true
                    }
                    is UiEvent.Success<*> -> {
                        binding.progressbar.isVisible = false
                        val quoteslist = it.data as List<AllTopicsResult>
                        quotesAdapter.submitList(quoteslist)
                        Log.d("TTT", it.data.toString())
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvquotes.apply {
            adapter = quotesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun clickItem(allTopicsResult: AllTopicsResult) {
        GetObjject.allTopicsResult = allTopicsResult

        Log.d("dataclick", GetObjject.allTopicsResult.name.toString())
        findNavController().navigate(R.id.ditailFragment)
    }


    private fun initDrawer() {
        binding.imgnav.setOnClickListener {
            binding.drawerl.openDrawer(Gravity.LEFT)
        }


        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menufavorite -> {
                    findNavController().navigate(R.id.likedFragment)
                    binding.drawerl.closeDrawers()
                }
            }
            true
        }

    }


}