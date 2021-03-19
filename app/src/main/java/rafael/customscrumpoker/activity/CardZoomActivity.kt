package rafael.customscrumpoker.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.card.*
import rafael.customscrumpoker.R


class CardZoomActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CARD_INFO = "EXTRA_CARD_INFO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_card)
        val intent = this.intent
        infoText.text = intent.getStringExtra(EXTRA_CARD_INFO)
    }

}