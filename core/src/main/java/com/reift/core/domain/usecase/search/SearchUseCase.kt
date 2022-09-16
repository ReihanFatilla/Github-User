package com.reift.core.domain.usecase.search

import com.reift.core.domain.entity.search.Search
import io.reactivex.rxjava3.core.Flowable

interface SearchUseCase {
    fun searchByUsername(username: String): Flowable<Search>
}