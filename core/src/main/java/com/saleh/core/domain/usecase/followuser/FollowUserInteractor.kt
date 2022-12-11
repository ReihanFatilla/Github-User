package com.saleh.core.domain.usecase.followuser

import com.saleh.core.domain.entity.followuser.FollowUser
import com.saleh.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class FollowUserInteractor(val repository: IUserRepository): FollowUserUseCase {
    override fun getFollowList(): Flow<List<FollowUser>> {
        return repository.getFollowList()
    }

    override fun getIdByUsername(username: String): Flow<FollowUser?> {
        return repository.getIdByUsername(username)
    }

    override fun addFollowUser(followUser: FollowUser) {
        repository.addFollowUser(followUser)
    }

    override fun deleteFollowUser(followUser: FollowUser) {
        repository.deleteFollowUser(followUser)
    }
}