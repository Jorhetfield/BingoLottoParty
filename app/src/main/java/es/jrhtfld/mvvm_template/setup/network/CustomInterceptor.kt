package es.jrhtfld.mvvm_template.setup.network

import android.util.Log
import es.jrhtfld.mvvm_template.BuildConfig
import es.jrhtfld.mvvm_template.setup.client.Prefs
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class CustomInterceptor(private val prefs: Prefs) : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(CustomInterceptor::class.java.simpleName, "Authorization: Bearer ${prefs.token}")

        val request = chain.request().newBuilder()
        request.addHeader(HttpHeaders.ACCEPT, "application/json")
        prefs.token?.let { authToken ->
            request
                .addHeader("Authorization", "Bearer $authToken")
                .addHeader("Lang", Locale.getDefault().language)
                .addHeader("platform", "android")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build()
        }

        return chain.proceed(request.build())
    }
}