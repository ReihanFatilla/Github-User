package com.reift.githubuser.presentation.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.reift.core.data.UserRepository
import com.reift.core.data.local.room.UserEntity

class FollowingViewModel(application: Application): AndroidViewModel(application) {

    private val repository = com.reift.core.data.UserRepository(application)

    fun getFollowList(): LiveData<List<com.reift.core.data.local.room.UserEntity>> {
        return repository.getFollowList()
    }
}