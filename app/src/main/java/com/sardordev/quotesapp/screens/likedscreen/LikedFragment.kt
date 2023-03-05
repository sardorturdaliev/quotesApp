package com.sardordev.quotesapp.screens.likedscreen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sardordev.quotesapp.R
import com.sardordev.quotesapp.databinding.FragmentLikedBinding
import com.sardordev.quotesapp.screens.likedscreen.adapter.LikedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikedFragment : Fragment() {
    private val binding by lazy { FragmentLikedBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LikedViewModel>()
    private val likedAdapter = LikedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel.getAllLiked.observe(viewLifecycleOwner, Observer {
            likedAdapter.submitList(it)
        })
        initRv()
        binding.leftarrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imgdeleteimg.setOnClickListener {
            deleteAllItems()
        }

        clickcopy()

        clickDetele()






        return binding.root
    }

    private fun initRv() {
        binding.rvliked.apply {
            adapter = likedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun clickcopy() {
        likedAdapter.setClickCopy {
            val clipboard =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("TextView", it.quote)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickDetele() {
        likedAdapter.setClickLike {
            viewModel.deleteLiked(it)
        }
    }

    private fun deleteAllItems(){
        viewModel.deleteAll()
    }

}