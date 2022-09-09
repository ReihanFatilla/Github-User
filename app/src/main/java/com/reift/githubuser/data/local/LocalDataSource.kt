package com.reift.githubuser.data.local

import com.reift.githubuser.data.local.datastore.ThemePreferences
import com.reift.githubuser.data.local.room.UserDao
import com.reift.githubuser.data.local.room.UserEntity

class LocalDataSource private constructor(
    private val userDao: UserDao,
    private val themePreference: ThemePreferences
) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(userDao: UserDao, themePreference: ThemePreferences): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(
                    userDao,
                    themePreference
                )
            }
    }

    fun insertFavorite(user: UserEntity) = userDao.insertFavorite(user)

    fun deleteFavorite(user: UserEntity) = userDao.deleteFavorite(user)

    fun updateFavorite(user: UserEntity) = userDao.updateFavorite(user)

    fun getThemeSetting() = themePreference.getThemeSetting()

    suspend fun saveThemeSetting(isDarkMode: Boolean) = themePreference.saveThemeSetting(isDarkMode)

}