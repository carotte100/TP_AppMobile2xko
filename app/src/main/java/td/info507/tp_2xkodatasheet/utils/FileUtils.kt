package td.info507.tp_2xkodatasheet.utils

import android.content.Context
import td.info507.tp_2xkodatasheet.storage.ChampionJSonFileStorage


fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

fun loadJsonFromAssets(context: Context, fileName: String): String {
    val inputStream = context.assets.open(fileName)
    return inputStream.bufferedReader().use { it.readText() }
}