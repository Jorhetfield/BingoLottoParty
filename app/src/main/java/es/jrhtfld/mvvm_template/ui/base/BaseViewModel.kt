package es.jrhtfld.mvvm_template.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    sealed class ScreenState<out T> {
        object LOADING : ScreenState<Nothing>()
        class RenderData<T>(val renderState: T) : ScreenState<T>()
    }

    private lateinit var job: Job
    protected lateinit var uiScope: CoroutineScope
    protected lateinit var ioContext: CoroutineContext

    fun init() {
        onCreate()
    }

    protected open fun onCreate() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
        ioContext.cancel()
    }
}