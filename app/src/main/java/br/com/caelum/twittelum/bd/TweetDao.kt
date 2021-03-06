package br.com.caelum.twittelum.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.caelum.twittelum.modelo.Tweet

@Dao
interface TweetDao {

    @Insert
    fun salva(tweet: Tweet)

    @Query("select * from Tweet")
    fun busca(): LiveData<List<Tweet>>

    @Delete
    fun exclui(tweet: Tweet)
}
