package com.saleh.core.data.local

import com.saleh.core.data.local.datastore.ThemeDataStore
import com.saleh.core.data.local.room.UserDao
import com.saleh.core.data.local.room.UserEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource (
    private val userDao: UserDao,
    private val themeDataStore: ThemeDataStore
){

    suspend fun insertFollowing(user: UserEntity) {
        userDao.insertFollowing(user)
    }

    suspend fun deleteFollowing(user: UserEntity) {
        userDao.deleteFollowing(user)
    }

    fun getIdByUsername(username: String): Flow<UserEntity?> {
        return userDao.getIdByUsername(username)
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