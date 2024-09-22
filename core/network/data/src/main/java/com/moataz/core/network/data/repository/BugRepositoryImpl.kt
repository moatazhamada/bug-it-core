package com.moataz.core.network.data.repository

import com.moataz.core.network.data.datasource.BugsLocalDataSource
import com.moataz.core.network.data.datasource.BugsRemoteDataSource
import com.moataz.core.network.data.remote.model.toBugEntity
import com.moataz.core.network.data.remote.model.toBugRemoteDTO
import com.moataz.core.network.domain.model.BugEntity
import com.moataz.core.network.domain.repository.BugRepository
import javax.inject.Inject


internal class BugRepositoryImpl @Inject constructor(
    private val bugsRemoteDataSources: Set<BugsRemoteDataSource>,
    private val bugsLocalDataSource: BugsLocalDataSource
) : BugRepository {
    override suspend fun uploadBug(bug: BugEntity): Result<Boolean> {
        var remoteResult = Result.success(true)
        bugsRemoteDataSources.forEach { remoteDataSource ->
            remoteDataSource.uploadBug(bug.toBugRemoteDTO()).also {
                if (it.isFailure) {
                    remoteResult = it
                }
            }
        }
        if (remoteResult.isSuccess) {
            bugsLocalDataSource.saveBug(bug) // Cache locally after successful remote upload
        }
        return remoteResult
    }

    override suspend fun getBugs(): List<BugEntity> {
        val localBugs = bugsLocalDataSource.getAllBugs()

        val remoteBugs = bugsRemoteDataSources.flatMap { it.getAllBugs() }.map { it.toBugEntity() }

        val combinedBugs = (localBugs + remoteBugs).distinctBy { it.id }

        remoteBugs.forEach { remoteBug ->
            val localBug = localBugs.find { it.id == remoteBug.id }
            if (localBug == null) {
                // If it's a new remote bug, save it to local data
                bugsLocalDataSource.saveBug(remoteBug)
            } else {
                if (remoteBug.description != localBug.description) {
                    bugsLocalDataSource.saveBug(remoteBug)
                }
            }
        }

        // Return the combined bugs
        return combinedBugs
    }
}