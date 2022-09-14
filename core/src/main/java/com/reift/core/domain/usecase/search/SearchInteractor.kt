package com.reift.core.domain.usecase.search

import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.repository.IUserRepository
import io.reactivex.rxjava3.core.Flowable

class SearchInteractor(val repository: IUserRepository): SearchUseCase {
    override fun searchByUsername(username: String): Flowable<Detail> {
        return repository.searchByUsername(username)
    }
}