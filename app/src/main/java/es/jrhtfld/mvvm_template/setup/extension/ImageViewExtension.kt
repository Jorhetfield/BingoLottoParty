package es.jrhtfld.mvvm_template.setup.extension

import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import coil.load

fun ImageView.loadImage(url: String) {
    load(url) {
        target {
            setImageDrawable(it)
        }
    }
}

fun ImageView.loadImage(url: Uri) {
    load(url) {
        target {
            setImageDrawable(it)
        }
    }
}

fun ImageView.loadImage(url: Bitmap) {
    load(url) {
        target {
            setImageDrawable(it)
        }
    }
}