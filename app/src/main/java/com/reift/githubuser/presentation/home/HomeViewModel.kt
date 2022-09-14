package com.reift.githubuser.presentation.home

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val repository = com.reift.core.data.UserRepository(application)
    private val themeRepository = com.reift.core.data.local.datastore.ThemeRepository(application)

    val userResponse = MutableLiveData<List<com.reift.core.data.network.response.search.UserResponseItem>?>()

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            themeRepository.saveThemeSetting(isDarkModeActive)
        }
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return themeRepository.getThemeSetting().asLiveData()
    }

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