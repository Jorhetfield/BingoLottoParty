package es.jrhtfld.mvvm_template.setup.extension

import android.util.Log
import es.jrhtfld.mvvm_template.setup.client.Prefs.Companion.FILTER

fun Any.logWTF(text: String) {
    Log.wtf("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logWTF(text: String, exception: Exception) {
    Log.wtf("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logInfo(text: String) {
    Log.i("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logInfo(text: String, exception: Exception) {
    Log.i("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logError(text: String) {
    Log.e("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logError(text: String, exception: Exception) {
    Log.e("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logWarn(text: String) {
    Log.w("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logWarn(text: String, exception: Exception) {
    Log.w("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logD(text: String) {
    Log.d("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logD(text: String, exception: Exception) {
    Log.d("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logVerbose(text: String) {
    Log.v("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logVerbose(text: String, exception: Exception) {
    Log.v("$FILTER: ${this::class.java.simpleName}", text, exception)
}