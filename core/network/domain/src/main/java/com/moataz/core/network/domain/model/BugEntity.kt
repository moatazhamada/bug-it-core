package com.moataz.core.network.domain.model

import java.util.UUID

data class BugEntity(
    val id: String = UUID.randomUUID().toString(),
    val screenshotUrl: String,
    val description: String
)