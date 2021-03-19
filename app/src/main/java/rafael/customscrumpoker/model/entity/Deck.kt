package rafael.customscrumpoker.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Deck(var name: String) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
}
