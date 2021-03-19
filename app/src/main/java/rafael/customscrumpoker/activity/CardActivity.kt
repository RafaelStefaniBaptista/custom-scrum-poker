package rafael.customscrumpoker.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_deck.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rafael.customscrumpoker.R
import rafael.customscrumpoker.activity.GameActivity.Companion.EXTRA_DECK_ID
import rafael.customscrumpoker.model.AppDatabase
import rafael.customscrumpoker.model.entity.Card

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        onTextChanged()
        button.setOnClickListener {
            addCard(intent.getLongExtra(EXTRA_DECK_ID, 0))
        }
    }

    fun onTextChanged() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                infoText.text = s.toString()
            }
        })
    }

    fun addCard(deckId: Long) {
        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "techstore-database"
        ).build()
        val cardDao = database.cardDao()
        GlobalScope.launch {
            try {
                val card = Card(deckId, editText.text.toString())
                val cardId = cardDao.add(card)
                GameActivity.cards.add(card)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CardActivity, cardId.toString(), Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}