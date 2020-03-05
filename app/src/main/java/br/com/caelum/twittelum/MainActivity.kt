package br.com.caelum.twittelum

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.ServiceLocator
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, ServiceLocator).get(TweetViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)

        return true //exibe o menu
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menuSalvarTweet -> {
            publicaTweet()
            finish()
            true
        }
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }


    private fun publicaTweet() {
        val tweet = criaTweet()
        viewModel.salva(tweet)
        Toast.makeText(this, "$tweet", Toast.LENGTH_LONG).show()
    }

    private fun criaTweet(): Tweet {
        val texto = conteudoTweet.text.toString()
        return Tweet(texto)
    }
}


