package br.com.caelum.twittelum

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.ServiceLocator
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var caminho: String? = null

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders
            .of(this, ServiceLocator)
            .get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        if (caminho != null) {
            exibeFoto()
        }
    }

    private fun exibeFoto() {

        val bitmapZuado = BitmapFactory.decodeFile(caminho)

        val bitmap = Bitmap.createScaledBitmap(bitmapZuado, 350, 300, true)

        tweetFoto.setImageBitmap(bitmap)

        tweetFoto.scaleType = ImageView.ScaleType.FIT_XY
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

        R.id.menuFotoTweet -> {


            val intencaoDeIrParaCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            intencaoDeIrParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, criaCaminhoFoto())
            startActivity(intencaoDeIrParaCamera)

            true
        }
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }

    private fun criaCaminhoFoto(): Uri? {
        caminho = "${getExternalFilesDir("fotos")}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(caminho)
        return FileProvider.getUriForFile(this,"TweetProvider" ,arquivo)
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

