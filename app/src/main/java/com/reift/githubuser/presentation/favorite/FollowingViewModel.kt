package com.reift.githubuser.presentation.favorite

import android.app.Application
import androidx.lifecycle.*
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.local.datastore.ThemeRepository
import com.reift.githubuser.data.local.room.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FollowingViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(application)

    fun getFollowList(): LiveData<List<UserEntity>> {
        return repository.getFollowList()
    }
}