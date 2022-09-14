package com.reift.core.data

import android.content.Context
import com.reift.core.data.local.LocalDataSource
import com.reift.core.data.remote.RemoteDataSource
import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.follow.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.repository.IUserRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
): IUserRepository{

    override fun searchByUsername(username: String): Flowable<Detail> {
        TODO("Not yet implemented")
    }

    override fun getUserDetail(username: String): Flowable<Detail> {
        TODO("Not yet implemented")
    }

    override fun getUserFollowing(username: String): Flowable<List<Follow>> {
        TODO("Not yet implemented")
    }

    override fun getUserFollowers(username: String): Flowable<List<Follow>> {
        TODO("Not yet implemented")
    }

    override fun getFollowList(): Flow<List<FollowUser>> {
        TODO("Not yet implemented")
    }

    override fun getIdByUsername(username: String): Flow<FollowUser> {
        TODO("Not yet implemented")
    }

    override fun getThemeSetting(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun saveThemeSetting(isDarkMode: Boolean) {
        TODO("Not yet implemented")
    }

}