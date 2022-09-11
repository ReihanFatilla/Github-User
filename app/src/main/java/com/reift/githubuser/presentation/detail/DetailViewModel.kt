package com.reift.githubuser.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.local.room.UserEntity
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.follow.FollowResponse

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository = UserRepository(application)

    val detailResponse = MutableLiveData<DetailResponse?>()
    val followingResponse = MutableLiveData<List<FollowResponse>?>()
    val followersResponse = MutableLiveData<List<FollowResponse>?>()

    fun insertFavorite(user: UserEntity) {
        repository.insertFavorite(user)
    }

    fun deleteFavorite(user: UserEntity) {
        repository.deleteFavorite(user)
    }

    fun updateFavorite(user: UserEntity) {
        repository.updateFavorite(user)
    }

    fun saveUserPref(key: String, value: Boolean){
        repository.put(key, value)
    }

    fun removeUserPref(key: String){
        repository.remove(key)
    }

    fun getBooleanPref(key: String): Boolean {
        return repository.getBoolean(key)
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