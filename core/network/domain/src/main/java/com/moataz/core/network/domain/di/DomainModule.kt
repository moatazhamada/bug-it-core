package com.moataz.core.network.domain.di

import com.moataz.core.network.domain.repository.BugRepository
import com.moataz.core.network.domain.usecase.FetchBugsUseCase
import com.moataz.core.network.domain.usecase.FetchBugsUseCaseImpl
import com.moataz.core.network.domain.usecase.UploadBugUseCase
import com.moataz.core.network.domain.usecase.UploadBugUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideFetchBugsUseCase(
        bugRepository: BugRepository
    ): FetchBugsUseCase {
        return FetchBugsUseCaseImpl(bugRepository)
    }

    @Provides
    @Singleton
    fun provideUploadBugUseCase(
        bugRepository: BugRepository
    ): UploadBugUseCase {
        return UploadBugUseCaseImpl(bugRepository)
    }
}