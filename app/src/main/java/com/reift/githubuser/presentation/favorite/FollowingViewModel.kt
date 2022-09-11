package com.reift.githubuser.presentation.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.local.datastore.ThemeRepository
import com.reift.githubuser.data.local.room.UserEntity

class FollowingViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(application)

    fun getFollowList(): LiveData<List<UserEntity>>{
        return repository.getFollowList()
    }
}