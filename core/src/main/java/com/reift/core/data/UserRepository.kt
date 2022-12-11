package com.reift.core.data

import com.reift.core.data.local.LocalDataSource
import com.reift.core.data.remote.RemoteDataSource
import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.entity.search.Search
import com.reift.core.domain.repository.IUserRepository
import com.reift.core.utils.DataMapper
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
): IUserRepository{

    override fun searchByUsername(username: String): Flowable<Search> {
        return remoteDataSource.searchByUsername(username).map {
            DataMapper.mapSearchResponseToDomain(it)
        }
    }

    override fun getUserDetail(username: String): Flowable<Detail> {
        return remoteDataSource.getUserDetail(username).map {
            DataMapper.mapDetailResponseToDomain(it)
        }
    }

    override fun getUserFollowing(username: String): Flowable<List<Follow>> {
        return remoteDataSource.getUserFollowing(username).map {
            DataMapper.mapFollowResponseToDomain(it)
        }
    }

    override fun getUserFollowers(username: String): Flowable<List<Follow>> {
        return remoteDataSource.getUserFollowers(username).map {
            DataMapper.mapFollowResponseToDomain(it)
        }
    }

    override fun getFollowList(): Flow<List<FollowUser>> {
        return localDataSource.getFollowList().map {
            DataMapper.mapListFollowEntityToDomain(it)
        }
    }

    override fun getIdByUsername(username: String): Flow<FollowUser> {
        return localDataSource.getIdByUsername(username).map {
            DataMapper.mapFollowEntityToDomain(it)
        }
    }

    override fun addFollowUser(followUser: FollowUser) {
        CoroutineScope(Dispatchers.IO).launch {
            val entity = DataMapper.mapDomainToEntity(followUser)
            localDataSource.insertFollowing(entity)
        }
    }

    override fun deleteFollowUser(followUser: FollowUser) {
        CoroutineScope(Dispatchers.IO).launch {
            val entity = DataMapper.mapDomainToEntity(followUser)
            localDataSource.deleteFollowing(entity)
        }
    }

    override fun getThemeSetting(): Flow<Boolean> {
        return localDataSource.getThemeSetting()
    }

    override fun saveThemeSetting(isDarkMode: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.saveThemeSetting(isDarkMode)
        }
    }

}