package com.moataz.core.network.data.remote

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface GoogleSheetApiService {
    @POST("sheets/v4/spreadsheets/{spreadsheetId}/values/{range}:append")
    suspend fun addToGoogleSheet(
        @Path("spreadsheetId") spreadsheetId: String,
        @Path("range") range: String,
        @Body requestBody: RequestBody
    ): Response<Any>
}

interface NotionApiService {
    @POST("v1/pages/{pageId}/properties/{propertyId}")
    suspend fun addToNotion(
        @Path("pageId") pageId: String,
        @Path("propertyId") propertyId: String,
        @Body requestBody: RequestBody
    ): Response<Any>
}
