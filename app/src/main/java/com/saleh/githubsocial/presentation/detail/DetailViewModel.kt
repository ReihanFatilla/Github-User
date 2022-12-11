package com.saleh.githubsocial.presentation.detail

import androidx.lifecycle.*
import com.saleh.core.domain.entity.detail.Detail
import com.saleh.core.domain.entity.detail.Follow
import com.saleh.core.domain.usecase.detail.DetailUseCase

class DetailViewModel(val detailUseCase: DetailUseCase): ViewModel() {

    fun getUserDetail(username: String): LiveData<Detail> {
        return LiveDataReactiveStreams.fromPublisher(
            detailUseCase.getUserDetail(username)
        )
    }

    fun getUserFollowers(username: String): LiveData<List<Follow>> {
        return LiveDataReactiveStreams.fromPublisher(
            detailUseCase.getUserFollowers(username)
        )
    }

    fun getUserFollowing(username: String): LiveData<List<Follow>> {
        return LiveDataReactiveStreams.fromPublisher(
            detailUseCase.getUserFollowing(username)
        )
    }
}