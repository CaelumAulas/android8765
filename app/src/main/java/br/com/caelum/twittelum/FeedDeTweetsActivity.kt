package br.com.caelum.twittelum

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.caelum.twittelum.bd.TwittelumDatabase
import br.com.caelum.twittelum.modelo.Tweet
import kotlinx.android.synthetic.main.activity_feed_de_tweets.*

class FeedDeTweetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_de_tweets)

        val tweetDao = TwittelumDatabase.getInstance(this).getTweetDao()
        val tweets = tweetDao.busca()

        listaTweets.adapter = ArrayAdapter<Tweet>(
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
