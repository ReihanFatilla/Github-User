package com.reift.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getFavoriteList(): LiveData<List<UserEntity>>

    @Insert
    suspend fun insertFavorite(user: UserEntity)

    @Update
    suspend fun updateFavorite(user: UserEntity)

    @Delete
    suspend fun removeFavorite(user: UserEntity)

}