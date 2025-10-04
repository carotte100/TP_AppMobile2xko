package td.info507.tp_2xkodatasheet.model

data class Coup (
    val type:String,
    val nom: String,
    val description : String,
    val icone : List<String>,
    val dataDeCoup: List<String>
) {
    companion object{
        const val TYPE ="Type"
        const val NOM ="Nom"
        const val DESCRIPTION="Description"
    }
}