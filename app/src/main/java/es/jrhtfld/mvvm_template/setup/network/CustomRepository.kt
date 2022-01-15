package es.jrhtfld.mvvm_template.setup.network

import android.content.Context
import es.jrhtfld.data.PathResponse
import es.jrhtfld.mvvm_template.BuildConfig
import es.jrhtfld.mvvm_template.R
import es.jrhtfld.mvvm_template.setup.client.CustomService
import es.jrhtfld.mvvm_template.setup.extension.getMockResponseResult
import es.jrhtfld.mvvm_template.setup.network.NetworkExceptionController.checkException
import es.jrhtfld.mvvm_template.setup.network.NetworkExceptionController.checkResponse
import es.jrhtfld.ineed.setup.network.ResponseResult
import kotlinx.coroutines.delay
import okhttp3.MultipartBody

class CustomRepository(private val service: CustomService, private val context: Context) {

    //region User

    suspend fun uploadImage(
        file: MultipartBody.Part,
        fake: Boolean = BuildConfig.MOCK
    ): ResponseResult<PathResponse> {
        return if (!fake) {
            try {
                val response = service.uploadImage(file)
                checkResponse(context, response)
            } catch (e: Exception) {
                checkException(context, e)
            }
        } else {
            context.getMockResponseResult(R.raw.user)
        }
    }


}