package com.example.random_api.di


import com.example.random_api.data.repository.RandomFactsRepositoryImpl
import com.example.random_api.domain.RandomFactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindRandomFactsRepo(
        randomFactsRepoImpl: RandomFactsRepositoryImpl
    ): RandomFactsRepository

}