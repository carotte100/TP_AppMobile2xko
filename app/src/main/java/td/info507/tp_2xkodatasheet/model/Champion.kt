package td.info507.tp_2xkodatasheet.model

data class Champion (
    val nom:String,
    val description: Description,
    val lCoup: ListeCoup,
    val lCombo: ListeCombo
    )