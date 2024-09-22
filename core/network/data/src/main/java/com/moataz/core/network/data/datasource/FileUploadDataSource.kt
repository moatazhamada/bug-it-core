package com.moataz.core.network.data.datasource

import android.net.Uri

interface FileUploadDataSource {
    suspend fun uploadFile(fileUri: Uri): Result<String>
}
