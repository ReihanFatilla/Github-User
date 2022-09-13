package com.reift.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getFollowList(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE login LIKE :username")
    fun getIdByUsername(username: String): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowing(user: UserEntity)

    @Delete
    suspend fun deleteFollowing(user: UserEntity)

}