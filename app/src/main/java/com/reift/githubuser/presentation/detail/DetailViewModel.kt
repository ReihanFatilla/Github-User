package com.reift.githubuser.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.data.network.response.search.ItemsItem

class DetailViewModel: ViewModel() {
    private val repository = UserRepository()

    val detailResponse = MutableLiveData<DetailResponse>()
    val followingResponse = MutableLiveData<List<FollowResponse>>()
    val followersResponse = MutableLiveData<List<FollowResponse>>()

    fun getUserDetail(username: String){
        repository.getUserDetail(
            username,
            {
                detailResponse.postValue(it)
            },
            {
                detailResponse.postValue(null)
            }
        )
    }

    fun getUserFollowers(username: String){
        repository.getUserFollowers(
            username,
            {
                followersResponse.postValue(it)
            },
            {
                followersResponse.postValue(null)
            }
        )
    }

    fun getUserFollowing(username: String){
        repository.getUserFollowing(
            username,
            {
                followingResponse.postValue(it)
            },
            {
                followingResponse.postValue(null)
            }
        )
    }
}