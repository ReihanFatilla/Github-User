package com.reift.githubuser.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.reift.githubuser.constant.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeRepository(
    context: Context
) {

    private val Context.dataStore by preferencesDataStore(Constant.PREF_SETTINGS)
    private val THEME_KEY = booleanPreferencesKey(Constant.PREF_THEME)
    private val settingDataStore = context.dataStore

    fun getThemeSetting(): Flow<Boolean> {
        return settingDataStore.data.map {
            it[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkMode: Boolean) {
        settingDataStore.edit {
            it[THEME_KEY] = isDarkMode
        }
    }

}