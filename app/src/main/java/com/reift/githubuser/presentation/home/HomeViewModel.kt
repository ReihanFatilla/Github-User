package com.reift.githubuser.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.network.response.search.UserItem

class HomeViewModel: ViewModel() {
    private val repository = UserRepository()

    val userResponse = MutableLiveData<List<UserItem>>()

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