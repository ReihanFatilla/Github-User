package com.reift.follow.followuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.usecase.followuser.FollowUserUseCase

class FollowUserViewModel(val followUserUseCase: FollowUserUseCase): ViewModel() {

    fun getFollowList(): LiveData<List<FollowUser>> {
        return followUserUseCase.getFollowList().asLiveData()
    }

}