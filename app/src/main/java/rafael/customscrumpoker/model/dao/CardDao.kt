package rafael.customscrumpoker.model.dao

import android.arch.persistence.room.*
import rafael.customscrumpoker.model.entity.Card

@Dao
interface CardDao {
    @Query("Select * from card")
    fun all(): MutableList<Card>
    @Query("Select * from card where deck_id = :deckId")
    fun all(deckId : Long): MutableList<Card>
    @Insert
    fun add(card: Card)
    @Delete
    fun remove(card: Card)
    @Update
    fun update(card: Card)
}