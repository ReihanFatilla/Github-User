package com.reift.core.domain.usecase.detail

import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import io.reactivex.rxjava3.core.Flowable

interface DetailUseCase {
    fun getUserDetail(username: String): Flowable<Detail>
    fun getUserFollowing(username: String): Flowable<List<Follow>>
    fun getUserFollowers(username: String): Flowable<List<Follow>>
}