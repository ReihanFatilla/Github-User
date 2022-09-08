package com.reift.githubuser.data

import com.reift.githubuser.data.network.ApiConfig
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.data.network.response.search.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository {

    private val apiService = ApiConfig.getApiService()

    fun searchByUsername(responseHandler : (UserResponse) -> Unit, username: String) {
        apiService.searchByUsername(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

    fun getUserDetail(responseHandler : (DetailResponse) -> Unit, username: String) {
        apiService.getUserDetail(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

    fun getUserFollowers(responseHandler : (List<FollowResponse>) -> Unit, username: String) {
        apiService.getUserFollowers(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

    fun getUserFollowing(responseHandler : (List<FollowResponse>) -> Unit, username: String) {
        apiService.getUserFollowing(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

}