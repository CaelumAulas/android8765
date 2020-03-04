package br.com.caelum.twittelum

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_feed_de_tweets.*

class FeedDeTweetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_de_tweets)

        val tweets = arrayListOf(
            "Hoje é terça e a galera tá com muito sono já :( ",
            "Pessoal quer me matar por estar falando de android hoje",
            "tem café, não durmam hahaha",
            "sei la",
            "hahah"
        )

        listaTweets.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            tweets
        )


        fabAdd.setOnClickListener {
            val intencao = Intent(this, MainActivity::class.java)
            startActivity(intencao)
        }

    }
}
