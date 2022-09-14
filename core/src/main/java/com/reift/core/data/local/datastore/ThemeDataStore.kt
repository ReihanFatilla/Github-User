package com.reift.core.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.reift.githubuser.constant.Constant

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeDataStore(
    context: Context
) {

    private val Context.dataStore by preferencesDataStore(Constant.PREF_SETTINGS)
    private val themeKey = booleanPreferencesKey(Constant.PREF_THEME)
    private val settingDataStore = context.dataStore

    fun getThemeSetting(): Flow<Boolean> {
        return settingDataStore.data.map {
            it[themeKey] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkMode: Boolean) {
        settingDataStore.edit {
            it[themeKey] = isDarkMode
        }
    }

}