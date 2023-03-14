package com.reift.githubuser.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.usecase.detail.DetailUseCase

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

    fun insertFollowing(user: FollowUser) {
        detailUseCase.addFollowUser(user)
    }

    fun deleteFollowing(user: FollowUser) {
        detailUseCase.deleteFollowUser(user)
    }

    fun getIdByUsername(username: String): LiveData<FollowUser?> {
        return detailUseCase.getIdByUsername(username).asLiveData()
    }

}