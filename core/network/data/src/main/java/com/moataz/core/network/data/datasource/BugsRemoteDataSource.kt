package com.moataz.core.network.data.datasource

import com.moataz.core.network.data.remote.model.BugRemoteDTO

interface BugsRemoteDataSource {
    suspend fun uploadBug(bug: BugRemoteDTO): Result<Boolean>
    suspend fun getAllBugs(): List<BugRemoteDTO>
}