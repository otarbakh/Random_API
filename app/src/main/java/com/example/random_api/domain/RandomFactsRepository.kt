package com.example.random_api.domain

import com.example.random_api.common.Resource
import com.example.random_api.data.model.RandomFactsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RandomFactsRepository {
    suspend fun getRandomFacts() : Flow<Resource<RandomFactsDto>>
}