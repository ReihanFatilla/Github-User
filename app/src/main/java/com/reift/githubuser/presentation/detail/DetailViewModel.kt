package com.reift.githubuser.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.reift.githubuser.presentation.favorite.FollowingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository = com.reift.core.data.UserRepository(application)
    private val followingVM = FollowingViewModel(application)

    val detailResponse = MutableLiveData<com.reift.core.data.remote.response.detail.DetailResponse?>()
    val followingResponse = MutableLiveData<List<com.reift.core.data.remote.response.follow.FollowResponse>?>()
    val followersResponse = MutableLiveData<List<com.reift.core.data.remote.response.follow.FollowResponse>?>()

    fun getIdByUsername(username: String) = repository.getIdByUsername(username)

    fun insertFollowing(user: com.reift.core.data.local.room.UserEntity) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insertFollowing(user)
        }
    }

    fun deleteFollowing(user: com.reift.core.data.local.room.UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFollowing(user)
        }
        followingVM.getFollowList()
    }

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