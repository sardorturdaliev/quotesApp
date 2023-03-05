package com.sardordev.quotesapp.data.api

import com.sardordev.quotesapp.data.model.AllTopicsResult
import com.sardordev.quotesapp.data.model.AloneBody
import com.sardordev.quotesapp.data.model.EmptyBody
import com.sardordev.quotesapp.data.model.ResultAlone
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiQuotos {

    @POST("topic")
    @Headers(
        "X-RapidAPI-Key:a8a0b22bafmsh860bf319ead8259p1d8b1ajsna514c38b7589",
        "X-RapidAPI-Host:quotel-quotes.p.rapidapi.com"
    )
    suspend fun getAloneData(@Body aloneBody: AloneBody): Response<List<ResultAlone>>


    @POST("topics")
    @Headers(
        "X-RapidAPI-Key:a8a0b22bafmsh860bf319ead8259p1d8b1ajsna514c38b7589",
        "X-RapidAPI-Host:quotel-quotes.p.rapidapi.com"
    )
    suspend fun getAllTopics(@Body emptyBody: EmptyBody): Response<List<AllTopicsResult>>



}