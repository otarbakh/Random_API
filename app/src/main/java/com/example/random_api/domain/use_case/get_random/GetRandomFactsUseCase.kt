package com.example.random_api.domain.use_case.get_random

import com.example.random_api.common.Resource
import com.example.random_api.data.model.RandomFactsDto
import com.example.random_api.domain.RandomFactsRepository
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import java.util.concurrent.Flow
import javax.inject.Inject

class GetRandomFactsUseCase @Inject constructor(
    private val repository:RandomFactsRepository
) {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<Resource<RandomFactsDto>> = channelFlow{
        repository.getRandomFacts().collect{
            when(it){
                is Resource.Success->{
                    send(Resource.Success(it.data))
                }
                is Resource.Error->{
                    send(Resource.Error(it.error))
                }
                is Resource.Loading-> {
                    send(Resource.Loading(it.loading))
                }

            }
        }
    }
}