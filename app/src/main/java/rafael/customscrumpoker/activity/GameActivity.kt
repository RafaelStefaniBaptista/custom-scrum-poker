package rafael.customscrumpoker.activity

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rafael.customscrumpoker.R
import rafael.customscrumpoker.adapter.CardRecyclerViewAdapter
import rafael.customscrumpoker.model.AppDatabase
import rafael.customscrumpoker.model.entity.Card

class GameActivity : AppCompatActivity() {
    companion object {
        lateinit var cards: MutableList<Card>
        const val EXTRA_DECK_ID = "EXTRA_DECK_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val deckId = intent.getLongExtra(EXTRA_DECK_ID, 0)
        loadCards(deckId)
        fab.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra(EXTRA_DECK_ID, deckId)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        rv.adapter?.notifyDataSetChanged()
    }

    fun loadCards(deckId : Long) {
        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "techstore-database"
        ).build()
        val cardDao = database.cardDao()
        GlobalScope.launch {
            cards = cardDao.all(deckId)
            withContext(Dispatchers.Main) {
                rv.adapter = CardRecyclerViewAdapter(cards, this@GameActivity)
                rv.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }
}