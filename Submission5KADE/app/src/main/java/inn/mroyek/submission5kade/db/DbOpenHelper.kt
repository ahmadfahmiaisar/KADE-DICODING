package inn.mroyek.submission5kade.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context,
    "favorite_football.db", null, 1) {
    companion object {
        private var instance: DbOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DbOpenHelper? {
            if (instance == null) {
                instance = DbOpenHelper(context.applicationContext)
            }
            return instance
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteEntity.TABLE_FAV, true,
            FavoriteEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteEntity.ID_MATCH to TEXT + UNIQUE,
            FavoriteEntity.ID_HOME_TEAM to TEXT,
            FavoriteEntity.ID_AWAY_TEAM to TEXT,
            FavoriteEntity.NAME_HOME_TEAM to TEXT,
            FavoriteEntity.NAME_AWAY_TEAM to TEXT,
            FavoriteEntity.SCORE_HOME to TEXT,
            FavoriteEntity.SCORE_AWAY to TEXT,
            FavoriteEntity.DATE_MATCH to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteEntity.TABLE_FAV, true)
    }
}
