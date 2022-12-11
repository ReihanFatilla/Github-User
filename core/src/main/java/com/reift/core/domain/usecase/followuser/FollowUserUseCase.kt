package com.reift.core.domain.usecase.followuser

import com.reift.core.domain.entity.followuser.FollowUser
import kotlinx.coroutines.flow.Flow

interface FollowUserUseCase {
    fun getFollowList(): Flow<List<FollowUser>>
    fun getIdByUsername(username: String): Flow<FollowUser>
}