package com.sardordev.quotesapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sardordev.quotesapp.data.entity.LikeEntity

@Dao
interface LikeDao {

    @Insert
    fun insert(likeEntity: LikeEntity)


    @Delete
    fun deleteItem(likeEntity: LikeEntity)


    @Query("delete  from likedata")
    fun deleteAll()

    @Query("select * from likedata")
    fun getAllData(): LiveData<List<LikeEntity>>



}