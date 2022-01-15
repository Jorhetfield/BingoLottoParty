package es.jrhtfld.mvvm_template.setup.koin

import android.content.Context
import androidx.room.Room
import es.jrhtfld.domain.provider.*
import es.jrhtfld.domain.repository.*
import es.jrhtfld.domain.room.ProfileDataBase
import es.jrhtfld.mvvm_template.setup.network.CustomRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    single<ProfileDataBase> { provideProfileRoom(androidContext()) }

    //MEDIA
    single { ExampleProvider(get()) }
    single { ExampleRepository(get()) }

    factory { CustomRepository(service = get(), context = get()) }
}

private fun provideProfileRoom(context: Context) = Room.databaseBuilder(context, ProfileDataBase::class.java, "profile").build()