package com.saleh.core.domain.usecase.followuser

import com.saleh.core.domain.entity.followuser.FollowUser
import kotlinx.coroutines.flow.Flow

interface FollowUserUseCase {
    fun getFollowList(): Flow<List<FollowUser>>
    fun getIdByUsername(username: String): Flow<FollowUser?>
    fun addFollowUser(followUser: FollowUser)
    fun deleteFollowUser(followUser: FollowUser)
}