package rafael.customscrumpoker.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_deck.*
import kotlinx.coroutines.*
import rafael.customscrumpoker.R
import rafael.customscrumpoker.model.AppDatabase
import rafael.customscrumpoker.model.entity.Deck



class DeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deck)
        onTextChanged()
        button.setOnClickListener(::addDeck)
    }

    fun onTextChanged() {
        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                infoText.text = s.toString()
            }
        })
    }

    fun addDeck(view : View) {
        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "techstore-database"
        ).build()
        val deckDao = database.deckDao()
        GlobalScope.launch {
            try {
                val deck = Deck(editText.text.toString())
                val deckId = deckDao.add(deck)
                deck.id = deckId
                MainActivity.decks.add(deck)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@DeckActivity, "Deck ${deck.name} criado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}