package com.saleh.githubsocial.presentation.followuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.saleh.core.domain.entity.followuser.FollowUser
import com.saleh.core.domain.usecase.followuser.FollowUserUseCase

class FollowUserViewModel(private val followUserUseCase: FollowUserUseCase): ViewModel() {

    fun getFollowList(): LiveData<List<FollowUser>> {
        return followUserUseCase.getFollowList().asLiveData()
    }

    fun insertFollowing(user: FollowUser) {
        followUserUseCase.addFollowUser(user)
    }

    fun deleteFollowing(user: FollowUser) {
        followUserUseCase.deleteFollowUser(user)
    }

    fun getIdByUsername(username: String): LiveData<FollowUser?> {
        return followUserUseCase.getIdByUsername(username).asLiveData()
    }

}