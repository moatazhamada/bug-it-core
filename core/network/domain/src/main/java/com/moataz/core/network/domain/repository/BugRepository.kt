package com.moataz.core.network.domain.repository

import com.moataz.core.network.domain.model.BugEntity

interface BugRepository {
    suspend fun uploadBug(bug: BugEntity): Result<Boolean>
    suspend fun getBugs(): List<BugEntity>
}