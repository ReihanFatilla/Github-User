package com.reift.core.data

import android.content.Context
import com.reift.core.data.local.room.UserDB
import com.reift.core.data.local.room.UserEntity
import com.reift.core.data.network.ApiConfig
import com.reift.core.data.network.ApiService
import com.reift.core.data.network.response.detail.DetailResponse
import com.reift.core.data.network.response.follow.FollowResponse
import com.reift.core.data.network.response.search.UserResponse
import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.follow.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.repository.IUserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow

class UserRepository(
    context: Context,
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