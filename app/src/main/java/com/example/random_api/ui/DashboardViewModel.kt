package com.example.random_api.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.random_api.common.Resource
import com.example.random_api.data.model.RandomFactsDto
import com.example.random_api.domain.use_case.get_random.GetRandomFactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel@Inject constructor(
    private val randomFactsUseCase: GetRandomFactsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<RandomFactsDto>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    fun getRandomFacts() {
        viewModelScope.launch {
            randomFactsUseCase().onEach { news ->
                when (news) {
                    is Resource.Success -> _state.value = Resource.Success(news.data)
                    is Resource.Error -> _state.value = Resource.Error("woops!")
                    is Resource.Loading -> _state.value = Resource.Loading(true)

                }
            }.launchIn(viewModelScope)

        }
    }
}