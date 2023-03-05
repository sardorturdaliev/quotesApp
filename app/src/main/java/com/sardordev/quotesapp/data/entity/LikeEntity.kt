package com.sardordev.quotesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likedata")
data class LikeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "quotecol")
    val quote : String,
    @ColumnInfo(name = "authorName")
    val authorName:String
)