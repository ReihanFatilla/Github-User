package com.reift.core.data.local

import androidx.lifecycle.LiveData
import com.reift.core.data.local.datastore.ThemeDataStore
import com.reift.core.data.local.room.UserDao
import com.reift.core.data.local.room.UserEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(
    private val userDao: UserDao,
    private val themeDataStore: ThemeDataStore
){
    fun insertFollowing(user: UserEntity) {
        userDao.insertFollowing(user)
    }

    fun deleteFollowing(user: UserEntity) {
        userDao.deleteFollowing(user)
    }

    fun getIdByUsername(username: String) {
        userDao.getIdByUsername(username)
    }

    fun getFollowList(): Flow<List<UserEntity>> {
        return userDao.getFollowList()
    }

    fun getThemeSetting(): Flow<Boolean>{
        return themeDataStore.getThemeSetting()
    }

    suspend fun saveThemeSetting(isDarkMode: Boolean){
        themeDataStore.saveThemeSetting(isDarkMode)
    }

}