package com.reift.core.domain.usecase.detail

import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.repository.IUserRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

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

    override fun getIdByUsername(username: String): Flow<FollowUser?> {
        return repository.getIdByUsername(username)
    }

    override fun addFollowUser(followUser: FollowUser) {
        repository.addFollowUser(followUser)
    }

    override fun deleteFollowUser(followUser: FollowUser) {
        return repository.deleteFollowUser(followUser)
    }
}