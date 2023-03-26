package com.reift.core.di

import androidx.room.Room
import com.reift.core.data.UserRepository
import com.reift.core.data.local.LocalDataSource
import com.reift.core.data.local.datastore.ThemeDataStore
import com.reift.core.data.local.room.UserDB
import com.reift.core.data.remote.RemoteDataSource
import com.reift.core.data.remote.network.ApiService
import com.reift.core.domain.repository.IUserRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single {
        val hostname = "api.github.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/Jg78dOE+fydIGk19swWwiypUSR6HWZybfnJG/8G7pyM=")
            .add(hostname, "sha256/e0IRz5Tio3GA1Xs4fUVWmH1xHDiH2dMbVtCBSkOIdqM=")
            .add(hostname, "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=")
            .build()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization" , "token $API_KEY")
                    .build()
                return@addInterceptor it.proceed(request)
            }
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("githubuser".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            UserDB::class.java, "user.database"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
    single {
        ThemeDataStore(androidContext())
    }
}

val dataSourceModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get(), get()) }
}

val repositoryModule = module {
    single<IUserRepository> { UserRepository(get(), get()) }
}

const val API_KEY = "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"
const val BASE_URL = "https://api.github.com/"