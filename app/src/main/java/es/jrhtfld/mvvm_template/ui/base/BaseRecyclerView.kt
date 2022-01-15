package es.jrhtfld.mvvm_template.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView<T, VH : BaseViewHolder<T>>(protected var itemList: List<T> = listOf()) :
    RecyclerView.Adapter<VH>() {

    fun updateList(currentList: List<T>) {
        itemList = currentList
        notifyDataSetChanged()
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = getViewHolder(parent, viewType)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(itemList[position])
    }
}