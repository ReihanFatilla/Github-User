package com.reift.core.domain.usecase.detail

import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    fun getUserDetail(username: String): Flowable<Detail>
    fun getUserFollowing(username: String): Flowable<List<Follow>>
    fun getUserFollowers(username: String): Flowable<List<Follow>>
    fun getIdByUsername(username: String): Flow<FollowUser?>
    fun addFollowUser(followUser: FollowUser)
    fun deleteFollowUser(followUser: FollowUser)
}