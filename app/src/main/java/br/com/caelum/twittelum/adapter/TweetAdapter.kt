package br.com.caelum.twittelum.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.imagem.Decodificador
import br.com.caelum.twittelum.modelo.Tweet
import kotlinx.android.synthetic.main.item_layout.view.*

class TweetAdapter(private val tweets: List<Tweet>) : BaseAdapter() {


    override fun getCount(): Int {
        return tweets.size
    }

    override fun getItem(position: Int): Tweet {
        return tweets[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val tweet = getItem(position)
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)

        view.item_conteudo.text = tweet.conteudo

        tweet.foto?.let {
            view.item_foto.visibility = View.VISIBLE
            view.item_foto.setImageBitmap(Decodificador.decodificaDaBase64ParaBitmap(it))
        }

        return view
    }


}
