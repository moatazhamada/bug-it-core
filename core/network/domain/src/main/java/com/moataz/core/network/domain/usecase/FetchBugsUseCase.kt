package com.moataz.core.network.domain.usecase

import com.moataz.core.network.domain.model.BugEntity
import com.moataz.core.network.domain.repository.BugRepository
import javax.inject.Inject

interface FetchBugsUseCase {
    suspend operator fun invoke(): List<BugEntity>
}

internal class FetchBugsUseCaseImpl @Inject constructor(
    private val bugRepository: BugRepository
) : FetchBugsUseCase {

    override suspend fun invoke() = bugRepository.getBugs()

}
