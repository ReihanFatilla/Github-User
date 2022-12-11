package com.saleh.core.di

import androidx.room.Room
import com.saleh.core.BuildConfig
import com.saleh.core.BuildConfig.API_KEY
import com.saleh.core.data.UserRepository
import com.saleh.core.data.local.LocalDataSource
import com.saleh.core.data.local.datastore.ThemeDataStore
import com.saleh.core.data.local.room.UserDB
import com.saleh.core.data.remote.RemoteDataSource
import com.saleh.core.data.remote.network.ApiService
import com.saleh.core.domain.repository.IUserRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single {
        OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization" , API_KEY)
                    .build()
                return@addInterceptor it.proceed(request)
            }
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<UserDB>().userDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDB::class.java, "user.database"
        ).fallbackToDestructiveMigration().build()
    }
    single {
        ThemeDataStore(androidContext())
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get(), get()) }
    single<IUserRepository> { UserRepository(get(), get()) }
}