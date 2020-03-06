package br.com.caelum.twittelum.repository

import br.com.caelum.twittelum.bd.TweetDao
import br.com.caelum.twittelum.modelo.Tweet

class TweetRepository(private val fonteDeDados: TweetDao) {

    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)

    fun busca() = fonteDeDados.busca()

    fun deleta(tweet: Tweet) = fonteDeDados.exclui(tweet)

}
