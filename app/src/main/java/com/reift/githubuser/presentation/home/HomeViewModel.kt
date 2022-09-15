package com.reift.githubuser.presentation.home

import androidx.lifecycle.*
import com.reift.core.domain.entity.search.Search
import com.reift.core.domain.entity.search.SearchItem
import com.reift.core.domain.usecase.search.SearchUseCase

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