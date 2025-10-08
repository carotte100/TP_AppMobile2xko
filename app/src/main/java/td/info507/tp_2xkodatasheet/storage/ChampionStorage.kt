package td.info507.tp_2xkodatasheet.storage

import android.content.Context
import android.content.SharedPreferences
import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.storage.utility.Storage

object ChampionStorage {

    private const val LOGIN = "login"

    private const val STORAGE = "storage"

    const val NONE = 0


    const val FILE_JSON = 1

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("td.info507.2xkoDB.preferences", Context.MODE_PRIVATE)
    }

    fun getLogin(context: Context): String? {
        return getPreferences(context).getString(LOGIN,"twokayow")
    }

    fun setLogin(context: Context, prefLogin: String) {
        getPreferences(context).edit().putString(LOGIN,prefLogin).apply()
    }

    fun getStorage(context: Context): Int {
        return getPreferences(context).getInt(LOGIN,NONE)
    }

    fun setStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<Champion> {
        lateinit var storage: Storage<Champion>
        when (getStorage(context)) {

            NONE -> storage = ChampionNoneStorage()

            FILE_JSON -> storage = ChampionJSonFileStorage(context)
        }
        return storage
    }

}