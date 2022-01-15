package es.jrhtfld.mvvm_template.ui.newgame

import es.jrhtfld.mvvm_template.setup.client.Prefs
import es.jrhtfld.mvvm_template.setup.extension.json
import es.jrhtfld.mvvm_template.ui.base.BaseViewModel

class NewGameViewModel(
    private val prefs: Prefs
) : BaseViewModel() {
    private val numberList: MutableList<es.jrhtfld.data.Number> = mutableListOf()

    private fun getNumberList(): MutableList<es.jrhtfld.data.Number> {
        for (i in 1..90) {
            numberList.add(es.jrhtfld.data.Number(i, false))
        }
        return numberList
    }

    fun getCurrentList() = getNumberList()

    fun setCurrentList(numberList: MutableList<es.jrhtfld.data.Number>) {
        prefs.gameOnGoing = numberList.json()
    }

}