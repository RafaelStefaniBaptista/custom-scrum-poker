package rafael.customscrumpoker.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.card.view.*
import rafael.customscrumpoker.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import rafael.customscrumpoker.activity.GameActivity
import rafael.customscrumpoker.model.entity.Deck


class DeckRecyclerViewAdapter(
    private val decks: List<Deck>,
    private val activity: AppCompatActivity
) : RecyclerView.Adapter<DeckRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = decks[position]
        holder.deckId = card.id
        holder.deckName.text = card.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.deck, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return decks.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var deckId: Long = 0
        var deckName: TextView = itemView.infoText

        init {
            itemView.setOnClickListener(::deckClick)
        }

        private fun deckClick(itemView: View) {
            val intent = Intent(itemView.context, GameActivity::class.java)
            intent.putExtra(GameActivity.EXTRA_DECK_ID, deckId)
            itemView.context.startActivity(intent)
        }
    }

}