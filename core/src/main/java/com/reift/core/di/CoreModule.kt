package com.reift.core.di

import androidx.room.Room
import com.reift.core.BuildConfig
import com.reift.core.BuildConfig.API_KEY
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
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single {
        val hostname = "tourism-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/paJOw+DTCx1KaSMeALtM5gXuxJN4lP04qMKhSXBFa9Y=")
            .add(hostname, "sha256/qPerI4uMwY1VrtRE5aBY8jIQJopLUuBt2+GDUWMwZn4=")
            .add(hostname, "sha256/iie1VXtL7HzAMF+/PVPR9xzT80kQxdZeJ+zduCB3uj0=")
            .build()

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
            .certificatePinner(certificatePinner)
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
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