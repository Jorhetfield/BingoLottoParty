package es.jrhtfld.mvvm_template

import androidx.multidex.MultiDexApplication
import es.jrhtfld.mvvm_template.setup.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(appModule, domainModule, useCaseModule, viewModuleModule, networkModule))
        }
    }

}