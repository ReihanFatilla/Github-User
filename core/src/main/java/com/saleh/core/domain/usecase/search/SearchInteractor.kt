package com.saleh.core.domain.usecase.search

import com.saleh.core.domain.entity.search.Search
import com.saleh.core.domain.repository.IUserRepository
import io.reactivex.rxjava3.core.Flowable

class SearchInteractor(val repository: IUserRepository): SearchUseCase {
    override fun searchByUsername(username: String): Flowable<Search> {
        return repository.searchByUsername(username)
    }
}