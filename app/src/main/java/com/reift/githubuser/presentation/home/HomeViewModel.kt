package com.reift.githubuser.presentation.home

import android.app.Application
import androidx.lifecycle.*
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.local.datastore.ThemeRepository
import com.reift.githubuser.data.network.response.search.UserItem
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val repository = UserRepository(application)
    private val themeRepository = ThemeRepository(application)

    val userResponse = MutableLiveData<List<UserItem>?>()

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