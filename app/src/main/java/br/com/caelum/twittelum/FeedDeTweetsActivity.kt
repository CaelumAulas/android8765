package br.com.caelum.twittelum

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.ServiceLocator
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_feed_de_tweets.*

class FeedDeTweetsActivity : AppCompatActivity() {

    private lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_de_tweets)

        viewModel = ViewModelProviders.of(this, ServiceLocator).get(TweetViewModel::class.java)

        fabAdd.setOnClickListener {
            val intencao = Intent(this, MainActivity::class.java)
            startActivity(intencao)
        }

        val tweetsLiveData = viewModel.busca()
        tweetsLiveData.observe(this, Observer { tweets ->
            listaTweets.adapter = ArrayAdapter<Tweet>(
                this,
                android.R.layout.simple_list_item_1,
                tweets
            )
        })
    }

}
