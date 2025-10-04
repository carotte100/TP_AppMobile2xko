package td.info507.tp_2xkodatasheet.storage

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.model.Coup
import td.info507.tp_2xkodatasheet.model.Description
import td.info507.tp_2xkodatasheet.model.ListeCombo
import td.info507.tp_2xkodatasheet.model.ListeCoup
import td.info507.tp_2xkodatasheet.storage.utility.file.JSONFileStorage


    class ChampionJSonFileStorage (context: Context) : JSONFileStorage<Champion>(context, "Champion") {
    override fun create(nom: String, obj: Champion): Champion {
        return Champion(nom, obj.description, obj.lCoup, obj.lCombo)
    }

    override fun objectToJson(nom: String, obj: Champion): JSONObject {
        val json = JSONObject()
        json.put("Nom", obj.nom)
        json.put("Description",descriptionToJson(obj.description))
        json.put("ListeCoup", listeCoupToJson(obj.lCoup))
        json.put("ListeCombo", listeComboToJson(obj.lCombo))
        return json
    }

    fun descriptionToJson(description: Description): JSONObject{
        val json = JSONObject()
        json.put(Description.DESCRIPTION, description.description)
        json.put(Description.VIDEO, description.video)
        return json
    }

    fun listeComboToJson(lcombo: ListeCombo): JSONArray{
        val comboJson = JSONArray()
        for (combo in lcombo.combo){
            val coupJson = JSONArray()
            for (coup in combo){
                coupJson.put(coup)
            }
            comboJson.put(coupJson)
        }
        return comboJson
    }

    fun listeCoupToJson(lcoup: ListeCoup): JSONArray {
        val jsonArray = JSONArray()
        for (coup in lcoup.lCoup) {
            jsonArray.put(coupToJson(coup))
        }
        return jsonArray
    }

    fun coupToJson(coup: Coup): JSONObject{
        val json = JSONObject()
        json.put(Coup.TYPE,coup.type)
        json.put(Coup.NOM,coup.nom)
        json.put(Coup.DESCRIPTION,coup.description)

        val iconeJson = JSONArray()
        for ( icone in coup.icone) {
            iconeJson.put(icone)
        }
        json.put("Icone", iconeJson)

        val frameDataJson = JSONArray()
        for (fd in coup.dataDeCoup) {
            frameDataJson.put(fd)
        }
        json.put("FrameData", frameDataJson)
        return json
    }


    override fun jsonToObject(json: JSONObject): Champion {
        return Champion(
            json.getString("Nom"),
            jsonToDescription(json.getJSONObject("Description")),
            jsonToListeCoup(json.getJSONArray("ListeCoup")),
            jsonToListeCombo(json.getJSONArray("ListeCombo"))
        )
    }

    fun jsonToDescription(json: JSONObject):Description{
        return Description(
            json.getString(Description.DESCRIPTION),
                json.getString(Description.VIDEO)
        )
    }

    fun jsonToListeCombo(json: JSONArray): ListeCombo{
        val lcombo = mutableListOf<List<String>>()
        for (combos in 0 until json.length()){
            val comboArray = json.getJSONArray(combos)
            val combo = mutableListOf<String>()
            repeat(comboArray.length()){ coup ->
                combo.add(comboArray.getString(coup))
            }
            lcombo.add(combo)
        }
        return ListeCombo(lcombo)
    }

    fun jsonToListeCoup(json: JSONArray): ListeCoup{
        val lcoup = mutableListOf<Coup>()
        repeat(json.length()) { coup ->
            val coupArray = json.getJSONObject(coup)
            lcoup.add(jsonToCoup(coupArray))
        }
        return ListeCoup(lcoup)
    }

    fun jsonToCoup(json: JSONObject): Coup{
        val icones = mutableListOf<String>()
        val iconeArray = json.getJSONArray("Icone")
        repeat( iconeArray.length()){ icone ->
            icones.add(iconeArray.getString(icone))
        }

        val fData = mutableListOf<String>()
        val fDataArray = json.getJSONArray("FrameData")
        repeat( iconeArray.length()){ data ->
            fData.add(fDataArray.getString(data))
        }
        return Coup(
            json.getString(Coup.TYPE),
            json.getString(Coup.NOM),
            json.getString(Coup.DESCRIPTION),
            icones,
            fData
        )
    }
}