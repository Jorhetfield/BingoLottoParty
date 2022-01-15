package es.jrhtfld.mvvm_template.ui.home

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.jrhtfld.mvvm_template.R
import es.jrhtfld.mvvm_template.databinding.HomeLayoutBinding
import es.jrhtfld.mvvm_template.setup.extension.logD
import es.jrhtfld.mvvm_template.setup.extension.visible
import es.jrhtfld.mvvm_template.ui.base.BaseFragment
import es.jrhtfld.mvvm_template.ui.customview.CustomToolbar
import kotlinx.android.synthetic.main.home_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeLayoutBinding>() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun initializeBinding(): HomeLayoutBinding {
        binding = HomeLayoutBinding.inflate(layoutInflater, constraintContainer, false)
        return binding
    }

    override fun setToolbar() {
        provideCustomToolbar().apply {
            initToolbar(CustomToolbar.ToolbarItemMenu.EmptyItem)
            visible()
            setToolbarBackGroundColor(resources.getColor(R.color.colorPrimaryDark, activity?.theme))
            setBigToolbarTitle(getString(R.string.mvvm_template))
        }
    }

    override fun initView() {
        homeViewModel.init()
        with(binding) {
            continueGameBtn.isEnabled = !prefs.gameOnGoing.isNullOrEmpty()
            continueGameBtn.isClickable = !prefs.gameOnGoing.isNullOrEmpty()
            newGameBtn.setOnClickListener {
                if (!prefs.gameOnGoing.isNullOrEmpty()) {
                    this@HomeFragment.context?.let { context ->
                        MaterialAlertDialogBuilder(context)
                            .setTitle("Tienes una partida guardada")
                            .setMessage("Quieres borrarla?")
                            .setPositiveButton("Sí, quiero empezar una nueva") { dialogInterface, i ->
                                prefs.gameOnGoing = ""
                                navController?.navigate(HomeFragmentDirections.actionHomeFragmentToNewGameFragment())
                                logD("Positive button clicked")
                            }
                            .setNegativeButton("No, continuar la anterior") { dialogInterface, i -> logD("negative button clicked") }
                            .show()
                    }
                } else navController?.navigate(HomeFragmentDirections.actionHomeFragmentToNewGameFragment())
            }
        }
    }
}