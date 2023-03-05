package com.sardordev.quotesapp.domain.repository

import androidx.lifecycle.LiveData
import com.sardordev.quotesapp.data.entity.LikeEntity
import com.sardordev.quotesapp.data.model.AllTopicsResult
import com.sardordev.quotesapp.data.model.AloneBody
import com.sardordev.quotesapp.data.model.EmptyBody
import com.sardordev.quotesapp.data.model.ResultAlone
import com.sardordev.quotesapp.utils.ResultEvent

interface AppRespository {

    suspend fun getAloneData(aloneBody: AloneBody): ResultEvent<List<ResultAlone>>

    suspend fun getAllTopics(emptyBody: EmptyBody): ResultEvent<List<AllTopicsResult>>

    fun insertData(likeEntity: LikeEntity)

    fun getLikedData(): LiveData<List<LikeEntity>>

    fun deleteItem(likeEntity: LikeEntity)

    fun deleteAll()
}