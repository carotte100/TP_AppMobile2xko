package td.info507.tp_2xkodatasheet.storage

import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.storage.utility.Storage

class ChampionNoneStorage: Storage<Champion> {
    override fun insert(key: String,obj: Champion) = Unit

    override fun size(): Int = 0

    override fun find(key: String): Champion? = null

    override fun findAll(): List<Champion> = emptyList()

    override fun delete(key: String) = Unit

    override fun update(key: String, obj: Champion) = Unit
}