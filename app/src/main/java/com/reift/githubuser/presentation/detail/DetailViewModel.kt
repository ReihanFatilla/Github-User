package com.reift.githubuser.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.local.room.UserEntity
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.presentation.favorite.FollowingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository = UserRepository(application)
    private val followingVM = FollowingViewModel(application)

    val detailResponse = MutableLiveData<DetailResponse?>()
    val followingResponse = MutableLiveData<List<FollowResponse>?>()
    val followersResponse = MutableLiveData<List<FollowResponse>?>()

    fun getIdByUsername(username: String) = repository.getIdByUsername(username)

    fun insertFollowing(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insertFollowing(user)
        }
    }

    fun deleteFollowing(user: UserEntity) {
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