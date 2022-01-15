package es.jrhtfld.mvvm_template.setup.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import es.jrhtfld.mvvm_template.ui.base.BaseRecyclerView

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun RecyclerView.initVerticalRecycler(
    adapter: BaseRecyclerView<*, *>,
    isGridLayout: Boolean = false,
    spanCount: Int = 3,
    block: (RecyclerView) -> Unit = {}
) = run {
    layoutManager =
        if (isGridLayout) GridLayoutManager(context, spanCount) else LinearLayoutManager(context)
    setHasFixedSize(true)
    this.adapter = adapter
    block.invoke(this)
}

fun RecyclerView.initHorizontalRecycler(
    adapter: BaseRecyclerView<*, *>,
    block: (RecyclerView) -> Unit = {}
) = run {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    setHasFixedSize(true)
    this.adapter = adapter
    block.invoke(this)
}

fun RecyclerView.smoothSnapToPosition(position: Int, snapMode: Int = LinearSmoothScroller.SNAP_TO_START) {
    val smoothScroller = object : LinearSmoothScroller(this.context) {
        override fun getVerticalSnapPreference(): Int = snapMode
        override fun getHorizontalSnapPreference(): Int = snapMode
    }
    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}

fun EditText.getStringText() = text.toString()

fun EditText.isNotEmpty() = getStringText().isNotEmpty()

fun EditText.onChange(text: (String) -> Unit) {

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            text(s.toString())
        }
    })
}

fun EditText.stopChange(text: (String) -> Unit) {

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().isEmpty()) text(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}