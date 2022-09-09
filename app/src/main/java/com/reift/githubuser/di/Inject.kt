package com.reift.githubuser.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.reift.githubuser.data.UserRepository
import com.reift.githubuser.data.local.LocalDataSource
import com.reift.githubuser.data.local.datastore.ThemePreferences
import com.reift.githubuser.data.local.room.UserDB
import com.reift.githubuser.data.local.room.UserDao
import com.reift.githubuser.data.network.ApiConfig
import com.reift.githubuser.data.network.ApiService

object Inject {

    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    fun provideLocalDataSource(context: Context, dataStore: DataStore<Preferences>): LocalDataSource{
        val userDao = UserDB.getInstance(context).userDao()
        return LocalDataSource.getInstance(userDao, ThemePreferences.getInstance(dataStore))
    }

    fun provideRepository(context: Context, dataStore: DataStore<Preferences>): UserRepository{
        return UserRepository.getInstance(
            provideApiService(),
            provideLocalDataSource(
                context,
                dataStore
            )
        )
    }

}