package com.reift.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getFavoriteList(): LiveData<List<UserEntity>>

    @Insert
    fun insertFavorite(user: UserEntity)

    @Update
    fun updateFavorite(user: UserEntity)

    @Delete
    fun deleteFavorite(user: UserEntity)

}