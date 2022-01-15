package es.jrhtfld.mvvm_template.ui.splash

import android.os.CountDownTimer
import es.jrhtfld.mvvm_template.databinding.SplashLayoutBinding
import es.jrhtfld.mvvm_template.setup.extension.launchActivity
import es.jrhtfld.mvvm_template.ui.base.BaseActivity
import es.jrhtfld.mvvm_template.ui.home.HomeActivity

class SplashActivity : BaseActivity<SplashLayoutBinding>() {

    override fun initializeBinding(): SplashLayoutBinding {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        val countdownTimer = object : CountDownTimer(1500, 500) {
            override fun onFinish() {
                launchActivity<HomeActivity>(finish = true)
            }

            override fun onTick(p0: Long) {

            }
        }
        countdownTimer.start()
    }
}