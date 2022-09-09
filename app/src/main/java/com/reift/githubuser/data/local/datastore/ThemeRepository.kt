package com.reift.githubuser.data.local.datastore

import com.reift.githubuser.data.local.datastore.ThemePreferences
import com.reift.githubuser.data.local.room.UserDao
import com.reift.githubuser.data.local.room.UserEntity

class ThemeRepository private constructor(
    private val userDao: UserDao,
    private val themePreference: ThemePreferences
) {

    fun getThemeSetting() = themePreference.getThemeSetting()

    suspend fun saveThemeSetting(isDarkMode: Boolean) = themePreference.saveThemeSetting(isDarkMode)

}