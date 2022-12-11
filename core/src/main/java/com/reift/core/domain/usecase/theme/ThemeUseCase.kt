package com.reift.core.domain.usecase.theme

import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ThemeUseCase {
    fun getThemeSetting(): Flow<Boolean>

    fun saveThemeSetting(isDarkMode: Boolean)
}