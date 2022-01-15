package es.jrhtfld.mvvm_template.ui.newgame

import android.speech.tts.TextToSpeech
import es.jrhtfld.data.Number
import es.jrhtfld.mvvm_template.R
import es.jrhtfld.mvvm_template.databinding.NewGameLayoutBinding
import es.jrhtfld.mvvm_template.setup.extension.logD
import es.jrhtfld.mvvm_template.setup.extension.visible
import es.jrhtfld.mvvm_template.ui.base.BaseFragment
import es.jrhtfld.mvvm_template.ui.customview.CustomToolbar
import kotlinx.android.synthetic.main.home_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class NewGameFragment : BaseFragment<NewGameLayoutBinding>() {

    private val newGameViewModel by viewModel<NewGameViewModel>()
    private var mTTS: TextToSpeech? = null
    private var numberList: MutableList<Number> = mutableListOf()
    private var pickedNumberToRepeat = Number(0, false)

    override fun initializeBinding(): NewGameLayoutBinding {
        binding = NewGameLayoutBinding.inflate(layoutInflater, constraintContainer, false)
        return binding
    }

    override fun setToolbar() {
        provideCustomToolbar().apply {
            initToolbar(CustomToolbar.ToolbarItemMenu.EmptyItem)
            visible()
            setToolbarBackGroundColor(resources.getColor(R.color.colorPrimaryDark, activity?.theme))
            setBigToolbarTitle("VÁMONOS")
        }
    }

    override fun initView() {
        newGameViewModel.init()
        numberList = newGameViewModel.getCurrentList()
        with(binding) {
            mTTS = TextToSpeech(activity) { i ->
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS!!.setLanguage(Locale.getDefault())
                    if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED
                    ) {
                        logD("Lang not supported")
                    } else {
                        logD("Lang supported")
                    }
                } else {
                    logD("tts error")
                }
            }
            getNewNumberBTN.setOnClickListener {
                randomNumber()
            }
            repeatLastNumberBTN.setOnClickListener {
                speak(pickedNumberToRepeat.number.toString())
            }
        }
    }

    fun randomNumber() {
        checkNumber(numberList.random())
    }

    fun checkNumber(pickedNumber: Number) {
        if (!pickedNumber.alreadyOut) {
            speak(pickedNumber.number.toString())
            binding.newGameTitle.text = pickedNumber.number.toString()
            numberList.find { it == pickedNumber }?.alreadyOut = true
            pickedNumber.alreadyOut = true
            pickedNumberToRepeat = pickedNumber
            logD("número nuevo $pickedNumber")
            newGameViewModel.setCurrentList(numberList)

            numberList.forEach {
                if (it.alreadyOut) logD("números que han salido ${it.number}")
            }
        } else {
            if (numberList.all { it.alreadyOut }) showToast("Se acabó la partida")
            else randomNumber()
            logD("número repetido $pickedNumber")
        }
    }


    private fun speak(textToSpeak: String) {
        mTTS?.speak("El $textToSpeak", TextToSpeech.QUEUE_ADD, null, "")

    }
}
