package com.saleh.core.domain.repository

import com.saleh.core.domain.entity.detail.Detail
import com.saleh.core.domain.entity.detail.Follow
import com.saleh.core.domain.entity.followuser.FollowUser
import com.saleh.core.domain.entity.search.Search
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun searchByUsername(username: String): Flowable<Search>

    fun getUserDetail(username: String): Flowable<Detail>
    fun getUserFollowing(username: String): Flowable<List<Follow>>
    fun getUserFollowers(username: String): Flowable<List<Follow>>

    fun getFollowList(): Flow<List<FollowUser>>
    fun getIdByUsername(username: String): Flow<FollowUser?>
    fun addFollowUser(followUser: FollowUser)
    fun deleteFollowUser(followUser: FollowUser)

    fun getThemeSetting(): Flow<Boolean>
    fun saveThemeSetting(isDarkMode: Boolean)
}