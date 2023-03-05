package com.sardordev.quotesapp.di

import com.sardordev.quotesapp.data.api.ApiQuotos
import com.sardordev.quotesapp.data.db.DatabaseLiked
import com.sardordev.quotesapp.domain.repository.AppRepositoryImp
import com.sardordev.quotesapp.domain.repository.AppRespository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun getApi(): ApiQuotos {
        return Retrofit.Builder()
            .baseUrl("https://quotel-quotes.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiQuotos::class.java)
    }

    @Singleton
    @Provides
    fun getRepository(apiQuotos: ApiQuotos,databaseLiked: DatabaseLiked): AppRespository {
        return AppRepositoryImp(apiQuotos,databaseLiked)
    }

    @Singleton
    @Provides
    fun getDatabase(): DatabaseLiked {
        return DatabaseLiked.getInstance()
    }


}