package com.reift.githubuser.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.reift.core.domain.usecase.theme.ThemeUseCase

class ThemeViewModel(private val themeUseCase: ThemeUseCase): ViewModel() {
    fun saveThemeSetting(isDarkModeActive: Boolean) {
        themeUseCase.saveThemeSetting(isDarkModeActive)
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return themeUseCase.getThemeSetting().asLiveData()
    }
}