package es.jrhtfld.mvvm_template.ui.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import es.jrhtfld.mvvm_template.R
import es.jrhtfld.mvvm_template.setup.client.Prefs
import es.jrhtfld.mvvm_template.setup.extension.isEmail
import es.jrhtfld.mvvm_template.setup.network.CustomRepository
import org.koin.android.ext.android.inject

abstract class BaseActivity<BINDING : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: BINDING
    protected val navController: NavController? by lazy { findNavController(R.id.nav_host) }
    val prefs by inject<Prefs>()
    val customRepository by inject<CustomRepository>()
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initializeBinding().root)
        initView()
        val window = this.window

        // clear FLAG_TRANSLUCENT_STATUS flag:

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        // finally change the color

        // finally change the color
//        window.statusBarColor = ContextCompat.getColor(this, R.color.elsha_grey_background)
    }

    fun addTextWatcherEmail(inputEmailLayout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmail()) {
                    inputEmailLayout.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmail()) {
                    inputEmailLayout.isErrorEnabled = false
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().isEmail()) {
                    inputEmailLayout.isErrorEnabled = true
                    inputEmailLayout.error = getString(R.string.error_email)
                }
            }
        }
    }

    fun showError(error: String, v: View?) {
        v?.let {
            with(Snackbar.make(v, error, Snackbar.LENGTH_SHORT)) {
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.black
                    )
                )
                val tv = view.findViewById(R.id.snackbar_text) as TextView
                tv.setTextColor(ContextCompat.getColor(v.context, R.color.white))
                show()
            }
        }
    }

    fun showMessage(message: String, v: View?) {
        v?.let {
            with(Snackbar.make(v, message, Snackbar.LENGTH_SHORT)) {
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
                val tv = view.findViewById(R.id.snackbar_text) as TextView
                tv.setTextColor(ContextCompat.getColor(v.context, R.color.white))
                show()
            }
        }
    }

    fun showProgressDialog() {
        this.let {
            dialog = Dialog(it).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(false)
                setContentView(R.layout.dialog_progress)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                show()
            }
        }
    }

    fun hideProgressDialog() {
        if (dialog != null) dialog?.dismiss()
    }

    abstract fun initializeBinding(): BINDING

    abstract fun initView()
}