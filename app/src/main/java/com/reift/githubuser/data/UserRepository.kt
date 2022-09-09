package com.reift.githubuser.data

import com.reift.githubuser.data.local.LocalDataSource
import com.reift.githubuser.data.local.room.UserEntity
import com.reift.githubuser.data.network.ApiConfig
import com.reift.githubuser.data.network.ApiService
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.data.network.response.search.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository private constructor(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
){

    fun insertFavorite(user: UserEntity) = localDataSource.insertFavorite(user)

    fun deleteFavorite(user: UserEntity) = localDataSource.deleteFavorite(user)

    fun updateFavorite(user: UserEntity) = localDataSource.updateFavorite(user)

    fun getThemeSetting() = localDataSource.getThemeSetting()

    suspend fun setThemeSetting(isDarkMode: Boolean) = localDataSource.saveThemeSetting(isDarkMode)

    fun searchByUsername(username: String, responseHandler : (UserResponse) -> Unit, errorHandler : (Throwable) -> Unit) {
        apiService.searchByUsername(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    responseHandler(it)
                },
                {
                    errorHandler(it)
                }
            )
    }

    fun getUserDetail(username: String, responseHandler : (DetailResponse) -> Unit, errorHandler : (Throwable) -> Unit) {
        apiService.getUserDetail(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    responseHandler(it)
                },
                {
                    errorHandler(it)
                }
            )
    }

    fun getUserFollowers(username: String, responseHandler : (List<FollowResponse>) -> Unit, errorHandler : (Throwable) -> Unit) {
        apiService.getUserFollowers(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    responseHandler(it)
                },
                {
                    errorHandler(it)
                }
            )
    }

    fun getUserFollowing(username: String, responseHandler : (List<FollowResponse>) -> Unit, errorHandler : (Throwable) -> Unit) {
        apiService.getUserFollowing(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseHandler(it)
                },
                {
                    errorHandler(it)
                }
            )
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService, localDataSource: LocalDataSource): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, localDataSource).apply {
                    instance = this
                }
            }
    }

}