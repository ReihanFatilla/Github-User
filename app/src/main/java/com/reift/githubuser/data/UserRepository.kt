package com.reift.githubuser.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.reift.githubuser.data.local.LocalDataSource
import com.reift.githubuser.data.local.datastore.ThemePreferences
import com.reift.githubuser.data.local.room.UserDB
import com.reift.githubuser.data.local.room.UserEntity
import com.reift.githubuser.data.network.ApiConfig
import com.reift.githubuser.data.network.ApiService
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.data.network.response.search.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository(context: Context, dataStore: DataStore<Preferences>){

    private val apiService: ApiService = ApiConfig.getApiService()
    private val dao = UserDB.getInstance(context).userDao()
    private val theme = ThemePreferences.getInstance(dataStore)
    private val localDataSource: LocalDataSource = LocalDataSource.getInstance(dao, theme)

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

}