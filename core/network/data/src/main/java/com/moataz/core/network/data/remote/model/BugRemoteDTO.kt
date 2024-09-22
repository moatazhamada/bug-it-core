package com.moataz.core.network.data.remote.model

import com.moataz.core.network.domain.model.BugEntity

data class BugRemoteDTO(
    val id: String,
    val description: String,
    val imageUrl: String
)

fun BugEntity.toBugRemoteDTO(): BugRemoteDTO {
    return BugRemoteDTO(
        id = id,
        description = description,
        imageUrl = screenshotUrl,
    )
}

fun BugRemoteDTO.toBugEntity(): BugEntity {
    return BugEntity(
        id = id,
        description = description,
        screenshotUrl = imageUrl,
    )
}
