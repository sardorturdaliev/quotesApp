package com.sardordev.quotesapp.screens.likedscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sardordev.quotesapp.data.entity.LikeEntity
import com.sardordev.quotesapp.domain.repository.AppRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikedViewModel @Inject constructor(private val appRespository: AppRespository) : ViewModel() {

    val getAllLiked: LiveData<List<LikeEntity>> = appRespository.getLikedData()

    fun deleteLiked(likeEntity: LikeEntity){
        appRespository.deleteItem(likeEntity)
    }

    fun deleteAll(){
        appRespository.deleteAll()
    }

}