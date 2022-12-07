package com.example.random_api.data.repository

import com.example.random_api.common.Resource
import com.example.random_api.data.RandomFactsService
import com.example.random_api.data.model.RandomFactsDto
import com.example.random_api.domain.RandomFactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class RandomFactsRepositoryImpl @Inject constructor(
    private val service:RandomFactsService
):RandomFactsRepository {

    override suspend fun getRandomFacts(): Flow<Resource<RandomFactsDto>> = flow{
        try {
            emit(Resource.Loading(true))
            val response = service.getRandomFacts()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        }
    }
}