package com.saleh.core.domain.usecase.search

import com.saleh.core.domain.entity.search.Search
import io.reactivex.rxjava3.core.Flowable

interface SearchUseCase {
    fun searchByUsername(username: String): Flowable<Search>
}