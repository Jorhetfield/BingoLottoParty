package es.jrhtfld.mvvm_template.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import es.jrhtfld.mvvm_template.R
import es.jrhtfld.mvvm_template.setup.extension.gone
import es.jrhtfld.mvvm_template.setup.extension.visible
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    sealed class ToolbarItemMenu {
        object EmptyItem : ToolbarItemMenu()
        object AgendaItem : ToolbarItemMenu()
        object SettingsItem : ToolbarItemMenu()
        object EditItem : ToolbarItemMenu()
        object AddFieldItem : ToolbarItemMenu()
        object AddEventItem : ToolbarItemMenu()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true)
        initToolbar(ToolbarItemMenu.EmptyItem)
    }

    fun initToolbar(itemMenu: ToolbarItemMenu) {
        when (itemMenu) {
            ToolbarItemMenu.EmptyItem -> setToolbarMenu(R.menu.empty_menu)
        }
    }

    private fun setToolbarMenu(@MenuRes menu: Int) {
        clearMenu()
        clearNavigationIcon()
        inflateMenu(menu)
    }

    private fun clearMenu() {
        menu.clear()
    }

    fun setOnMenuItemClick(
        onSettingsClick: () -> Unit = {},
        onExpandCalendarClick: () -> Unit = {},
        onFavouriteClick: () -> Unit = {},
        onCreateClick: () -> Unit = {},
        onCancelClick: () -> Unit = {},
        onDoneClick: () -> Unit = {},
        onEditClick: () -> Unit = {},
        onProfilePictureClick: () -> Unit = {}
    ) {

        setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.edit_item -> {
//                    onEditClick.invoke()
//                    true
//                }
//                else -> true
//            }
            true
        }
        profilePictureButton.setOnClickListener {
            onProfilePictureClick.invoke()
        }
        customToolbarCancel.setOnClickListener { onCancelClick.invoke() }
        customToolbarDone.setOnClickListener { onDoneClick.invoke() }

    }

    fun setBigToolbarTitle(title: String) {
        customToolbarTitle.visible()
        customToolbarTitle.text = title
        customToolbarTitleSmall.gone()
    }

    fun setSmallToolbarTitle(title: String) {
        customToolbarTitleSmall.visible()
        customToolbarTitleSmall.text = title
        customToolbarTitle.gone()
    }

    fun hideProfilePictureSettings() {
        profilePictureButton.gone()
    }

    fun showDoneCancelButtons() {
        customToolbarCancel.visible()
        customToolbarDone.visible()
    }

    fun hideDoneCancelButtons() {
        customToolbarCancel.gone()
        customToolbarDone.gone()
    }

    fun setToolbarTitleColor(@ColorRes color: Int) {
        customToolbarTitle.setTextColor(resources.getColor(color, null))
    }

    fun setToolbarBackGroundColor(@ColorInt color: Int) {
        setBackgroundColor(color)
    }

    fun setNavIcon(@DrawableRes icon: Int, onClick: () -> Unit) {
        setNavigationIcon(icon)
        setNavigationOnClickListener { onClick.invoke() }
    }

    private fun clearNavigationIcon() {
        navigationIcon = null
    }
}