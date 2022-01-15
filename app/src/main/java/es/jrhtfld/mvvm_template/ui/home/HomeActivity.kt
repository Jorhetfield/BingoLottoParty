package es.jrhtfld.mvvm_template.ui.home

import android.content.Context
import android.content.Intent
import android.view.Menu
import androidx.navigation.NavDirections
import es.jrhtfld.mvvm_template.databinding.BaseLayoutBinding
import es.jrhtfld.mvvm_template.setup.extension.gone
import es.jrhtfld.mvvm_template.setup.extension.visible
import es.jrhtfld.mvvm_template.ui.base.BaseActivity
import es.jrhtfld.mvvm_template.ui.home.HomeFragmentDirections.*

class HomeActivity : BaseActivity<BaseLayoutBinding>() {


    override fun initializeBinding(): BaseLayoutBinding {
        binding = BaseLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {

        with(binding) {}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    fun provideCustomToolbar() = binding.customToolbar

    private fun navigateToFragment(action: NavDirections) {
        navController?.navigate(action)
    }

    fun hideLoading() {
        binding.progressBar.gone()
    }

    fun showLoading() {
        binding.progressBar.visible()
    }

    companion object {
        @JvmStatic
        fun intent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}
