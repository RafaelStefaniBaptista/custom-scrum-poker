package rafael.customscrumpoker.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import rafael.customscrumpoker.model.dao.CardDao
import rafael.customscrumpoker.model.dao.DeckDao
import rafael.customscrumpoker.model.entity.Card
import rafael.customscrumpoker.model.entity.Deck

@Database(entities = [Deck::class, Card::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
}