package com.moataz.core.network.data.providers

import com.moataz.core.network.data.datasource.BugsLocalDataSource
import com.moataz.core.network.data.local.dao.BugDao
import com.moataz.core.network.data.local.model.toBugEntity
import com.moataz.core.network.data.local.model.toBugEntityLocal
import com.moataz.core.network.domain.model.BugEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomLocalProvider @Inject constructor(
    private val bugDao: BugDao
) : BugsLocalDataSource {

    override suspend fun saveBug(bug: BugEntity): Result<Boolean> {
        try {
            bugDao.saveBug(bug.toBugEntityLocal()).also {
                return Result.success(true)
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getAllBugs(): List<BugEntity> {
        return bugDao.getAllBugs().map {
            it.toBugEntity()
        }
    }
}
