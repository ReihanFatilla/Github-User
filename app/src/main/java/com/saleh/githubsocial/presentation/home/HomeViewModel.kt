package com.saleh.githubsocial.presentation.home

import androidx.lifecycle.*
import com.saleh.core.domain.entity.search.SearchItem
import com.saleh.core.domain.usecase.search.SearchUseCase

class HomeViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel() {
    val searchResponse = MediatorLiveData<List<SearchItem>>()

    fun searchByUsername(username: String) {
        val source = LiveDataReactiveStreams.fromPublisher(searchUseCase.searchByUsername(username))

        searchResponse.addSource(source) {
            searchResponse.postValue(it.items)
            searchResponse.removeSource(source)
        }
    }
}