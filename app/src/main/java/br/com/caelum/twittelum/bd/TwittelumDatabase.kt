package br.com.caelum.twittelum.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.caelum.twittelum.modelo.Tweet

@Database(entities = [Tweet::class], version = 2)
abstract class TwittelumDatabase : RoomDatabase() {

    abstract fun getTweetDao(): TweetDao

    companion object {

        private var twittelumDatabase: TwittelumDatabase? = null

        fun getInstance(contexto: Context): TwittelumDatabase {
            return twittelumDatabase ?: criaBanco(contexto).also { twittelumDatabase = it }
        }

        private fun criaBanco(contexto: Context): TwittelumDatabase {
            return Room.databaseBuilder(
                contexto,
                TwittelumDatabase::class.java,
                "TwittelumDatabase"
            )
                .allowMainThreadQueries()
                .addMigrations(Migracao1Para2)
                .build()
        }
    }

}


object Migracao1Para2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table Tweet add column foto text;")
    }

}










