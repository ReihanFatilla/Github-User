package com.reift.githubuser.presentation.home

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val repository = com.reift.core.data.UserRepository(application)
    private val themeDataStore = com.reift.core.data.local.datastore.ThemeDataStore(application)

    val userResponse = MutableLiveData<List<com.reift.core.data.remote.response.search.UserResponseItem>?>()

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            themeDataStore.saveThemeSetting(isDarkModeActive)
        }
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return themeDataStore.getThemeSetting().asLiveData()
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