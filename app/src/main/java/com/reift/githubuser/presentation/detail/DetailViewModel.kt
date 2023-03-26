package com.reift.githubuser.presentation.detail

import androidx.lifecycle.*
import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.usecase.detail.DetailUseCase
import io.reactivex.rxjava3.core.Flowable

class DetailViewModel(val detailUseCase: DetailUseCase): ViewModel() {

    val userDetailResponse = MediatorLiveData<Detail>()
    val userFollowerResponse = MediatorLiveData<List<Follow>>()
    val userFollowingResponse = MediatorLiveData<List<Follow>>()

    fun getUserDetail(username: String): Flowable<Detail> {
        val source = LiveDataReactiveStreams.fromPublisher(
            detailUseCase.getUserDetail(username)
        )

        userDetailResponse.addSource(source){
            userDetailResponse.postValue(it)
            userDetailResponse.removeSource(source)
        }

        return detailUseCase.getUserDetail(username)
    }

    fun getUserFollowers(username: String): Flowable<List<Follow>> {
        val source = LiveDataReactiveStreams.fromPublisher(
            detailUseCase.getUserFollowers(username)
        )

        userFollowerResponse.addSource(source){
            userFollowerResponse.postValue(it)
            userFollowerResponse.removeSource(source)
        }

        return detailUseCase.getUserFollowers(username)
    }

    fun getUserFollowing(username: String): Flowable<List<Follow>> {
        val source = LiveDataReactiveStreams.fromPublisher(
            detailUseCase.getUserFollowing(username)
        )

        userFollowingResponse.addSource(source){
            userFollowingResponse.postValue(it)
            userFollowingResponse.removeSource(source)
        }

        return detailUseCase.getUserFollowing(username)
    }

    fun insertFollowing(user: FollowUser) {
        detailUseCase.addFollowUser(user)
    }

    fun deleteFollowing(user: FollowUser) {
        detailUseCase.deleteFollowUser(user)
    }

    fun getIdByUsername(username: String): LiveData<FollowUser?> {
        return detailUseCase.getIdByUsername(username).asLiveData()
    }

}