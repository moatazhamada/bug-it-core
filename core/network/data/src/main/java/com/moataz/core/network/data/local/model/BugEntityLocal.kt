package com.moataz.core.network.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moataz.core.network.domain.model.BugEntity


@Entity(tableName = "bugs")
data class BugEntityLocal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val description: String,
    val screenshotUrl: String // Add other fields as necessary
)

fun BugEntityLocal.toBugEntity(): BugEntity {
    return BugEntity(
        id = id.toString(),
        description = description,
        screenshotUrl = screenshotUrl
    )
}

fun BugEntity.toBugEntityLocal(): BugEntityLocal {
    return BugEntityLocal(
        id = id.toLong(),
        description = description,
        screenshotUrl = screenshotUrl
    )
}