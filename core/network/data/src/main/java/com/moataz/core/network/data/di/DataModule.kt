package com.moataz.core.network.data.di

import android.app.Application
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moataz.core.network.data.datasource.BugsLocalDataSource
import com.moataz.core.network.data.datasource.BugsRemoteDataSource
import com.moataz.core.network.data.datasource.FileUploadDataSource
import com.moataz.core.network.data.local.BugDatabase
import com.moataz.core.network.data.local.dao.BugDao
import com.moataz.core.network.data.providers.FirebaseFileUploadProvider
import com.moataz.core.network.data.providers.GoogleSheetsProvider
import com.moataz.core.network.data.providers.NotionProvider
import com.moataz.core.network.data.providers.RoomLocalProvider
import com.moataz.core.network.data.remote.GoogleSheetApiService
import com.moataz.core.network.data.remote.NotionApiService
import com.moataz.core.network.data.repository.BugRepositoryImpl
import com.moataz.core.network.domain.repository.BugRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named(GoogleSheetsProvider.PROVIDER_NAME)
    fun provideGoogleSheetsRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GoogleSheetsProvider.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named(NotionProvider.PROVIDER_NAME)
    fun provideNotionRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NotionProvider.BASE_URL) // Notion base URL
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleSheetsApi(@Named(GoogleSheetsProvider.PROVIDER_NAME) retrofit: Retrofit): GoogleSheetApiService {
        return retrofit.create(GoogleSheetApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNotionApi(@Named(NotionProvider.PROVIDER_NAME) retrofit: Retrofit): NotionApiService {
        return retrofit.create(NotionApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideFileUploadRepository(firebaseStorage: FirebaseStorage): FileUploadDataSource {
        return FirebaseFileUploadProvider(firebaseStorage)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): BugDatabase {
        return BugDatabase.getDatabase(application)
    }

    @Provides
    @Singleton
    fun provideBugDao(database: BugDatabase): BugDao {
        return database.bugDao()
    }

    @Provides
    @Singleton
    fun provideRoomLocalProvider(
        bugDao: BugDao
    ): BugsLocalDataSource {
        return RoomLocalProvider(bugDao)
    }

    @Provides
    @IntoSet
    fun provideGoogleSheetsRemoteDataSource(
        googleSheetsApi: GoogleSheetApiService
    ): BugsRemoteDataSource {
        return GoogleSheetsProvider(googleSheetsApi)
    }

    // Provide Notion Remote Data Source
    @Provides
    @IntoSet
    fun provideNotionRemoteDataSource(
        notionApi: NotionApiService
    ): BugsRemoteDataSource {
        return NotionProvider(notionApi)
    }

    @Provides
    @Singleton
    fun provideBugRepository(
        bugsRemoteDataSource: Set<@JvmSuppressWildcards BugsRemoteDataSource>,
        bugsLocalDataSource: BugsLocalDataSource
    ): BugRepository {
        return BugRepositoryImpl(bugsRemoteDataSource, bugsLocalDataSource)
    }

}

