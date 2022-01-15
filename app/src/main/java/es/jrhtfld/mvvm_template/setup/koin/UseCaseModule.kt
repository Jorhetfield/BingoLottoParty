package es.jrhtfld.mvvm_template.setup.koin

import es.jrhtfld.domain.usecase.ExampleUseCase
import org.koin.dsl.module

val useCaseModule = module {

    //CHAT
    factory { ExampleUseCase(get()) }

}