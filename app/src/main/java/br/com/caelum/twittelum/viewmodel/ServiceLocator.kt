package br.com.caelum.twittelum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelum.app.TwittelumApp
import br.com.caelum.twittelum.bd.TweetDao
import br.com.caelum.twittelum.bd.TwittelumDatabase
import br.com.caelum.twittelum.repository.TweetRepository

object ServiceLocator : ViewModelProvider.NewInstanceFactory() {
    private val contexto = TwittelumApp.getInstance()
    private val database: TwittelumDatabase = TwittelumDatabase.getInstance(contexto)
    private val dao: TweetDao = database.getTweetDao()
    private val repository: TweetRepository = TweetRepository(dao)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TweetViewModel(repository) as T
    }
}
