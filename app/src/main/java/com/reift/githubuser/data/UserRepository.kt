package com.reift.githubuser.data

import com.reift.githubuser.data.network.ApiConfig
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.data.network.response.search.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository {

    private val apiService = ApiConfig.getApiService()

    fun searchByUsername(username: String, responseHandler : (UserResponse) -> Unit) {
        apiService.searchByUsername(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

    fun getUserDetail(username: String, responseHandler : (DetailResponse) -> Unit) {
        apiService.getUserDetail(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

    fun getUserFollowers(username: String, responseHandler : (List<FollowResponse>) -> Unit) {
        apiService.getUserFollowers(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

    fun getUserFollowing(username: String, responseHandler : (List<FollowResponse>) -> Unit) {
        apiService.getUserFollowing(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                responseHandler(it)
            }
    }

}