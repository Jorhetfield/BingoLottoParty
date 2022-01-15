package es.jrhtfld.mvvm_template.setup.koin

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import es.jrhtfld.domain.client.CustomClient
import es.jrhtfld.domain.error.NetworkController
import es.jrhtfld.mvvm_template.BuildConfig
import es.jrhtfld.mvvm_template.setup.client.CustomClientImpl
import es.jrhtfld.mvvm_template.setup.client.CustomService
import es.jrhtfld.mvvm_template.setup.network.NetworkExceptionController
import es.jrhtfld.mvvm_template.setup.network.CustomInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(CustomService::class.java)
    }

    factory {
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                when {
                    message.contains(" <-- END HTTP") -> println(" API-INFO-END: $message")
                    message.contains(" --> ") && !message.contains(" --> END") -> println(" API-INFO-REQUEST: $message")
                    message.contains(" <-- ") -> println(" API-INFO-CODE: $message")
                    message.contains(" [") -> println(" API-INFO-BODY: $message")
                    message.contains(" {") -> println(" API-INFO-BODY: $message")
                    message.contains(" (") -> println(" API-INFO-BODY: $message")
                }
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    factory { CustomInterceptor(prefs = get()) }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<CustomInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>()).build()
    }

    single<CustomClient> { CustomClientImpl(get(), get()) }

    single { NetworkExceptionController }

    single { NetworkController() }
}