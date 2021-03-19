package rafael.customscrumpoker.adapter

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.card.view.*
import rafael.customscrumpoker.R
import rafael.customscrumpoker.activity.CardZoomActivity
import rafael.customscrumpoker.activity.CardZoomActivity.Companion.EXTRA_CARD_INFO
import rafael.customscrumpoker.model.entity.Card


class CardRecyclerViewAdapter(
    private val cards: List<Card>,
    private val activity: AppCompatActivity
) : RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.infoText.text = card.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.card, parent, false)
        view.setOnClickListener(::zoomCard)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    private fun zoomCard(itemView: View) {
        val intent = Intent(itemView.context, CardZoomActivity::class.java)
        intent.putExtra(EXTRA_CARD_INFO, itemView.infoText.text.toString())
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, itemView, "zoom")
        activity.startActivity(intent, options.toBundle())
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var infoText: TextView = itemView.infoText
    }

}