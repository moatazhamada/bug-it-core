package com.moataz.core.network.domain.model

import android.net.Uri

data class Bug(
    val id: String,
    val description: String,
    val imageUri:Uri
)

fun Bug.toBugEntity(): BugEntity {
    return BugEntity(
        id = id,
        description = description,
        screenshotUrl = imageUri.toString()
    )

}