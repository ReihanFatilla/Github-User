package com.reift.core.utils

import android.content.Context
import com.reift.core.data.local.room.UserDB
import com.reift.core.data.local.room.UserEntity
import com.reift.core.data.remote.network.ApiConfig
import com.reift.core.data.remote.network.ApiService
import com.reift.core.data.remote.response.detail.DetailResponse
import com.reift.core.data.remote.response.follow.FollowResponse
import com.reift.core.data.remote.response.search.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepositoryBackUp(
    context: Context,
){

    private val apiService: ApiService = ApiConfig.getApiService()
    private val userDao = UserDB.getInstance(context).userDao()





}