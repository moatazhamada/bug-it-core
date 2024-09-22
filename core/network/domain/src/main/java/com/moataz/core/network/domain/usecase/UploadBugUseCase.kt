package com.moataz.core.network.domain.usecase
import com.moataz.core.network.domain.model.BugEntity
import com.moataz.core.network.domain.repository.BugRepository
import javax.inject.Inject

interface UploadBugUseCase {
    suspend operator fun invoke(bugEntity: BugEntity): Result<Boolean>
}

internal class UploadBugUseCaseImpl @Inject constructor(
    private val bugRepository: BugRepository
) : UploadBugUseCase {

    override suspend fun invoke(bugEntity: BugEntity): Result<Boolean> {
        return bugRepository.uploadBug(bugEntity)
    }
}
