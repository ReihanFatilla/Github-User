package com.reift.githubuser.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.network.response.search.UserItem

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val repository = UserRepository(application)

    val userResponse = MutableLiveData<List<UserItem>?>()

    fun searchByUsername(username: String){
        repository.searchByUsername(
            username,
            {
                userResponse.postValue(it.items)
            },
            {
                userResponse.postValue(null)
            }
        )
    }
}