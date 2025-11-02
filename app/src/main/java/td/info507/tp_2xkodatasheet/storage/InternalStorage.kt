package td.info507.tp_2xkodatasheet.storage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import td.info507.tp_2xkodatasheet.model.Champion
import java.io.File
import java.io.FileOutputStream

fun saveFavoriteChampions(context: Context, favorites: List<Champion>) {
    val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    val storage = ChampionJSonFileStorage(context)
    val jsonArray = org.json.JSONArray()

    for (champion in favorites) {
        // Utiliser objectToJson pour avoir le JSON complet correct
        val championJson = storage.objectToJson(champion.nom, champion)
        jsonArray.put(championJson)
    }

    prefs.edit().putString("favorite_champions", jsonArray.toString()).apply()
}

fun loadFavoriteChampions(context: Context): MutableList<Champion> {
    val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    val jsonString = prefs.getString("favorite_champions", "[]") ?: "[]"
    val storage = ChampionJSonFileStorage(context)
    val jsonArray = org.json.JSONArray(jsonString)
    val list = mutableListOf<Champion>()

    for (i in 0 until jsonArray.length()) {
        val jsonObj = jsonArray.getJSONObject(i)
        list.add(storage.jsonToObject(jsonObj))
    }

    return list
}


fun saveImageToInternalStorage(context: Context, bitmap: Bitmap, filename: String): String {
    val file = File(context.filesDir, "$filename.png")
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file.absolutePath
}

fun loadImageFromInternalStorage(path: String): Bitmap? {
    val file = File(path)
    return if (file.exists()) BitmapFactory.decodeFile(file.absolutePath) else null
}

fun saveImagePath(context: Context, path: String) {
    val prefs = context.getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)
    prefs.edit().putString("profile_image_path", path).apply()
}

fun loadImagePath(context: Context): String? {
    val prefs = context.getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)
    return prefs.getString("profile_image_path", null)
}

fun savePseudo(context: Context, pseudo: String) {
    val prefs = context.getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)
    prefs.edit().putString("user_pseudo", pseudo).apply()
}

fun loadPseudo(context: Context): String {
    val prefs = context.getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)
    return prefs.getString("user_pseudo", "") ?: ""
}
