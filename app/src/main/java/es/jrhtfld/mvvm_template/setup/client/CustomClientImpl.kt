package es.jrhtfld.mvvm_template.setup.client

import es.jrhtfld.data.*
import es.jrhtfld.domain.client.CustomClient
import es.jrhtfld.domain.error.NetworkController
import es.jrhtfld.mvvm_template.setup.extension.logD
import okhttp3.MultipartBody

class CustomClientImpl(
    private val customService: CustomService,
    private val networkController: NetworkController
) : CustomClient {


    override suspend fun exampleClientService(file: MultipartBody.Part): Either<String, PathResponse> {
        return try {
            val response = customService.uploadImage(file)
            logD(response.toString())
            networkController.checkResponse(response)
        } catch (e: Exception) {
            networkController.checkException(e)
        }
    }

}