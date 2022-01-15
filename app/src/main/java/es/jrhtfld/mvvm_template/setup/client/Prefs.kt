package es.jrhtfld.mvvm_template.setup.client

import android.content.SharedPreferences

class Prefs(private val sharedPrefs: SharedPreferences) {

    //region Vars
    companion object {
        const val TOKEN = "TOKEN"
        const val FAMILY_MEMBER_LIST = "FAMILY_MEMBER_LIST"
        const val FILTER = "FILTER"
        const val EVENT_TODAY_LIST = "EVENT_TODAY_LIST"
        const val GAME_ON_GOING = "GAME_ON_GOING"
    }

    //endregion

    //region UserPrefs

    var token: String?
        get() = sharedPrefs.getString(TOKEN, "")
        set(value) = sharedPrefs.edit().putString(TOKEN, value).apply()

    var gameOnGoing: String?
        get() = sharedPrefs.getString(GAME_ON_GOING, "")
        set(value) = sharedPrefs.edit().putString(GAME_ON_GOING, value).apply()

    //region Clear and remove Prefs
    fun clear(): Boolean {
        return sharedPrefs.edit().clear().commit()
    }


    private fun remove(key: String) {
        sharedPrefs.edit().remove(key).apply()
    }

    //endregion
}