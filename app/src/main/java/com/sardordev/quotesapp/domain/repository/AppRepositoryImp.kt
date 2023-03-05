package com.sardordev.quotesapp.domain.repository

import androidx.lifecycle.LiveData
import com.sardordev.quotesapp.data.api.ApiQuotos
import com.sardordev.quotesapp.data.db.DatabaseLiked
import com.sardordev.quotesapp.data.entity.LikeEntity
import com.sardordev.quotesapp.data.model.AllTopicsResult
import com.sardordev.quotesapp.data.model.AloneBody
import com.sardordev.quotesapp.data.model.EmptyBody
import com.sardordev.quotesapp.data.model.ResultAlone
import com.sardordev.quotesapp.utils.ResultEvent
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(
    private val apiQuotos: ApiQuotos,
    private val databaseLiked: DatabaseLiked
) : AppRespository {
    override suspend fun getAloneData(aloneBody: AloneBody): ResultEvent<List<ResultAlone>> {
        return try {
            val result = apiQuotos.getAloneData(aloneBody)
            if (result.isSuccessful) {
                ResultEvent.Success(result.body())
            } else {
                ResultEvent.Error(result.message())
            }
        } catch (e: Exception) {
            ResultEvent.Error(e.message)
        }
    }
    override suspend fun getAllTopics(emptyBody: EmptyBody): ResultEvent<List<AllTopicsResult>> {
        return try {
            val result = apiQuotos.getAllTopics(emptyBody)
            if (result.isSuccessful) {
                ResultEvent.Success(result.body())
            } else {
                ResultEvent.Error(result.message())
            }
        } catch (e: Exception) {
            ResultEvent.Error(e.message)
        }
    }
    override fun insertData(likeEntity: LikeEntity) {
        databaseLiked.getLikeDao().insert(likeEntity)
    }
    override fun getLikedData(): LiveData<List<LikeEntity>> {
        return databaseLiked.getLikeDao().getAllData()
    }

    override fun deleteItem(likeEntity: LikeEntity) {
        databaseLiked.getLikeDao().deleteItem(likeEntity)
    }

    override fun deleteAll() {
        databaseLiked.getLikeDao().deleteAll()
    }
}