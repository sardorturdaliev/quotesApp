package com.sardordev.quotesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sardordev.quotesapp.data.dao.LikeDao
import com.sardordev.quotesapp.data.entity.LikeEntity

@Database(entities = [LikeEntity::class], version = 1)
abstract class DatabaseLiked : RoomDatabase() {
    abstract fun getLikeDao(): LikeDao


    companion object {
        private var instance: DatabaseLiked? = null
        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, DatabaseLiked::class.java, "likedb")
                    .allowMainThreadQueries().build()
            }
        }
        fun getInstance() = instance!!
    }

}