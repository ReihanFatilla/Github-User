package com.reift.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getFollowList(): LiveData<List<UserEntity>>

    @Insert
    suspend fun insertFollowing(user: UserEntity)

    @Delete
    suspend fun deleteFollowing(user: UserEntity)

}