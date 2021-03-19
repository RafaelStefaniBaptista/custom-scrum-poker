package rafael.customscrumpoker.model.dao

import android.arch.persistence.room.*
import rafael.customscrumpoker.model.entity.Deck

@Dao
interface DeckDao {
    @Query("Select * from deck")
    fun all(): MutableList<Deck>
    @Insert
    fun add(deck: Deck): Long
    @Delete
    fun remove(deck: Deck)
    @Update
    fun update(deck: Deck)
}