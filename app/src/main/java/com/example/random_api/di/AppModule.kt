package com.example.random_api.di

import com.example.random_api.common.BaseUrl
import com.example.random_api.data.RandomFactsService
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
    fun provideRandomFacts(): RandomFactsService =
        Retrofit.Builder()
            .baseUrl(BaseUrl.RANDOM_FACTS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomFactsService::class.java)
}