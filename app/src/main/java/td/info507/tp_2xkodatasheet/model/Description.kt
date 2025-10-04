package td.info507.tp_2xkodatasheet.model

data class Description(
    val description:String,
    val video:String
) {
    companion object{
        const val DESCRIPTION="Description"
        const val VIDEO="Video"
    }
}