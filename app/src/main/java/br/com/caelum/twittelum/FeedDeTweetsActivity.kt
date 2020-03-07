package br.com.caelum.twittelum

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.ServiceLocator
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_feed_de_tweets.*

class FeedDeTweetsActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders
            .of(this, ServiceLocator)
            .get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_de_tweets)

        fabAdd.setOnClickListener {
            val intencao = Intent(this, TweetActivity::class.java)
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

        listaTweets.setOnItemClickListener { adapter, view, position, id ->

            val tweet = adapter.getItemAtPosition(position) as Tweet
            perguntaSePrecisaDeletar(tweet)
        }

    }

    private fun perguntaSePrecisaDeletar(tweet: Tweet) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Atenção")
        alert.setIcon(R.drawable.ic_warning)
        alert.setMessage("Você quer deletar o tweet com o seguinte conteudo ? \n`${tweet.conteudo}`")
        alert.setPositiveButton("Sim") { _, _ -> viewModel.deleta(tweet) }
        alert.setNegativeButton("Não", null)
        alert.setCancelable(false)
        alert.show()
    }

}
