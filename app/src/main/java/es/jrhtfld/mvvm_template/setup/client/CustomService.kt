package es.jrhtfld.mvvm_template.setup.client

import es.jrhtfld.data.PathResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface CustomService {

    @Multipart
    @POST("/api/upload-image")
    suspend fun uploadImage(@Part file: MultipartBody.Part): Response<PathResponse>

}