package com.moataz.core.network.data.datasource

import com.moataz.core.network.domain.model.BugEntity

interface BugsLocalDataSource {
    suspend fun saveBug(bug: BugEntity): Result<Boolean>
    suspend fun getAllBugs(): List<BugEntity>
}