package com.reift.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDB: RoomDatabase() {
    abstract fun userDao(): UserDao
}