package com.moataz.core.network.data.providers

import com.google.gson.Gson
import com.moataz.core.network.data.datasource.BugsRemoteDataSource
import com.moataz.core.network.data.remote.NotionApiService
import com.moataz.core.network.data.remote.model.BugRemoteDTO
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class NotionProvider @Inject constructor(
    private val apiService: NotionApiService
) : BugsRemoteDataSource {

    override suspend fun uploadBug(bug: BugRemoteDTO): Result<Boolean> {
        return try {
            val data = mapOf(
                "Description" to bug.description,
                "ImageURL" to bug.imageUrl
            )
            val requestBody = Gson().toJson(data)
                .toRequestBody("application/json".toMediaTypeOrNull())
            apiService.addToNotion("your_page_id", "your_property_id", requestBody)
            Result.success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getAllBugs(): List<BugRemoteDTO> {
        TODO("Not yet implemented")
    }

    companion object {
        const val PROVIDER_NAME: String = "Notion"
        const val BASE_URL = "https://api.notion.com/"
    }

}