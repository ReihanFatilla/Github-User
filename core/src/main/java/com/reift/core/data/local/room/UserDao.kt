package com.reift.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getFollowList(): Flow<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE login LIKE :username")
    fun getIdByUsername(username: String): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowing(user: UserEntity)

    @Delete
    fun deleteFollowing(user: UserEntity)

}