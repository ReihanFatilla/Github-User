package com.reift.githubuser.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.network.response.search.ItemsItem
import com.reift.githubuser.data.network.response.search.UserResponse

class MainViewModel: ViewModel() {
    private val repository = UserRepository()

    val userResponse = MutableLiveData<List<ItemsItem>>()

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