package com.saleh.core.domain.usecase.theme

import kotlinx.coroutines.flow.Flow

interface ThemeUseCase {
    fun getThemeSetting(): Flow<Boolean>

    fun saveThemeSetting(isDarkMode: Boolean)
}