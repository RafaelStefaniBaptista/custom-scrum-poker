package rafael.customscrumpoker.activity

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rafael.customscrumpoker.R
import rafael.customscrumpoker.adapter.DeckRecyclerViewAdapter
import rafael.customscrumpoker.model.AppDatabase
import rafael.customscrumpoker.model.entity.Deck

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var decks: MutableList<Deck>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        loadDecks()
        fab.setOnClickListener {
            val intent = Intent(this, DeckActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        rv.adapter?.notifyDataSetChanged()
    }

    private fun loadDecks() {
        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "techstore-database"
        ).build()
        val deckDao = database.deckDao()
        GlobalScope.launch {
            decks = deckDao.all()
            withContext(Dispatchers.Main) {
                rv.adapter = DeckRecyclerViewAdapter(decks, this@MainActivity)
                rv.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
