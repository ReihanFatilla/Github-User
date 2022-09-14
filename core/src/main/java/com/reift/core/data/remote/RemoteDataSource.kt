package com.reift.core.data.remote

import com.reift.core.data.remote.network.ApiService
import com.reift.core.data.remote.response.detail.DetailResponse
import com.reift.core.data.remote.response.follow.FollowResponse
import com.reift.core.data.remote.response.search.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class RemoteDataSource private constructor(
    private val apiService: ApiService
) {
    fun searchByUsername(username: String): Flowable<UserResponse> {

        val result = PublishSubject.create<UserResponse>()

        apiService.searchByUsername(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result.onNext(
                    it
                )
            }

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getUserDetail(username: String): Flowable<DetailResponse> {
        val result = PublishSubject.create<DetailResponse>()

        apiService.getUserDetail(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                result.onNext(
                    it
                )
            }

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getUserFollowers(username: String): Flowable<List<FollowResponse>> {

        val result = PublishSubject.create<List<FollowResponse>>()

        apiService.getUserFollowers(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result.onNext(
                    it
                )
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getUserFollowing(username: String): Flowable<List<FollowResponse>> {

        val result = PublishSubject.create<List<FollowResponse>>()

        apiService.getUserFollowing(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result.onNext(
                    it
                )
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}