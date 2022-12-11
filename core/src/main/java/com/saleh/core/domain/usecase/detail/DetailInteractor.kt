package com.saleh.core.domain.usecase.detail

import com.saleh.core.domain.entity.detail.Detail
import com.saleh.core.domain.entity.detail.Follow
import com.saleh.core.domain.repository.IUserRepository
import io.reactivex.rxjava3.core.Flowable

class DetailInteractor(val repository: IUserRepository): DetailUseCase {
    override fun getUserDetail(username: String): Flowable<Detail> {
        return repository.getUserDetail(username)
    }

    override fun getUserFollowing(username: String): Flowable<List<Follow>> {
        return repository.getUserFollowing(username)
    }

    override fun getUserFollowers(username: String): Flowable<List<Follow>> {
        return repository.getUserFollowers(username)
    }
}