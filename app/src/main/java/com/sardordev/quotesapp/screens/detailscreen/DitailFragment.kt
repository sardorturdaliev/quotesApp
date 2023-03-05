package com.sardordev.quotesapp.screens.detailscreen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sardordev.quotesapp.R
import com.sardordev.quotesapp.data.entity.LikeEntity
import com.sardordev.quotesapp.data.model.AllTopicsResult
import com.sardordev.quotesapp.data.model.AloneBody
import com.sardordev.quotesapp.data.model.ResultAlone
import com.sardordev.quotesapp.databinding.FragmentDitailBinding
import com.sardordev.quotesapp.`object`.GetObjject
import com.sardordev.quotesapp.screens.detailscreen.adapter.DetailAdapter
import com.sardordev.quotesapp.utils.UiEvent
import com.sardordev.quotesapp.veiwmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DitailFragment : Fragment() {
    private val binding by lazy { FragmentDitailBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<DetailViewModel>()
    private val detailAdapter = DetailAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel.getAloneData(AloneBody(0, 20, GetObjject.allTopicsResult.name.toString()))
        binding.tvQuotosName.text = GetObjject.allTopicsResult.name

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        getTopics()
        initRv()
        clickcopy()
        clickLike()

        return binding.root
    }

    private fun getTopics() {
        lifecycleScope.launchWhenCreated {
            viewModel.aloneobserver.collectLatest {
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
                        val quotesList = it.data as List<ResultAlone>
                        detailAdapter.submitList(quotesList)
                        Log.d("SSS", it.data.toString())
                    }
                }
            }
        }
    }

    private fun initRv() {
        binding.rvallquotes.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun clickcopy() {
        detailAdapter.setClickCopy {
            val clipboard =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("TextView", it.quote)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
        }
    }


    private fun clickLike() {
        detailAdapter.setClickLike {
            viewModel.insertLike(LikeEntity(0, it.quote, it.name))
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
        }
    }


}