package com.reift.githubuser.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.reift.githubuser.data.local.room.UserDB
import com.reift.githubuser.data.local.room.UserEntity
import com.reift.githubuser.data.local.sharedpref.PreferenceHelper
import com.reift.githubuser.data.network.ApiConfig
import com.reift.githubuser.data.network.ApiService
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.data.network.response.search.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository(
    context: Context,
){

    private val apiService: ApiService = ApiConfig.getApiService()
    private val userDao = UserDB.getInstance(context).userDao()
    private val pref = PreferenceHelper(context)

    fun putPref(key: String, value: Boolean){
        pref.put(key, value)
    }

    fun removePref(key: String){
        pref.remove(key)
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key)
    }

    fun getIdByUsername(username: String) = userDao.getIdByUsername(username)

    suspend fun insertFollowing(user: UserEntity) = userDao.insertFollowing(user)

    suspend fun deleteFollowing(user: UserEntity) = userDao.deleteFollowing(user)

    fun getFollowList(): LiveData<List<UserEntity>> = userDao.getFollowList()

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