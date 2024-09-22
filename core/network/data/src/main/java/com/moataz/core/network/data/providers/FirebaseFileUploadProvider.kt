package com.moataz.core.network.data.providers

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.moataz.core.network.data.datasource.FileUploadDataSource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFileUploadProvider @Inject constructor(
    private val firebaseStorage: FirebaseStorage
) : FileUploadDataSource {

    override suspend fun uploadFile(fileUri: Uri): Result<String> {
        val storageRef = firebaseStorage.reference.child("uploads/${System.currentTimeMillis()}")
        val uploadTask = storageRef.putFile(fileUri).await()

        return if (uploadTask.task.isSuccessful) {
            Result.success(storageRef.downloadUrl.await().toString())
        } else {
            Result.failure(Exception("File upload failed"))
        }
    }
}
