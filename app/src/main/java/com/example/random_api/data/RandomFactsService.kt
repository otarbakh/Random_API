package com.example.random_api.data

import com.example.random_api.data.model.RandomFactsDto
import retrofit2.Response
import retrofit2.http.GET

interface RandomFactsService {
    @GET("/fact/random")
    suspend fun getRandomFacts(): Response<RandomFactsDto>
}