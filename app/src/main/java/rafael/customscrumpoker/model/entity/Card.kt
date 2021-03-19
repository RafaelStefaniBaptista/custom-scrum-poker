package rafael.customscrumpoker.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Deck::class, parentColumns = ["id"], childColumns = ["deck_id"])])
class Card(@ColumnInfo(name = "deck_id") var deckId: Long, var text: String) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
}
