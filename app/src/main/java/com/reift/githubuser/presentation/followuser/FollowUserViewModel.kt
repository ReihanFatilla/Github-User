package com.reift.githubuser.presentation.followuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.usecase.followuser.FollowUserUseCase
import kotlinx.coroutines.flow.Flow

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