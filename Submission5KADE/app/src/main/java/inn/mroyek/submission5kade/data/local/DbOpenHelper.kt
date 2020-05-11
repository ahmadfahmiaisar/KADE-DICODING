package inn.mroyek.submission5kade.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbOpenHelper(context: Context) : ManagedSQLiteOpenHelper(
    context,
    "favorite_football.db", null, 1
) {
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
        db?.createTable(
            FavoriteMatchEntity.TABLE_FAV, true,
            FavoriteMatchEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatchEntity.ID_MATCH to TEXT + UNIQUE,
            FavoriteMatchEntity.ID_HOME_TEAM to TEXT,
            FavoriteMatchEntity.ID_AWAY_TEAM to TEXT,
            FavoriteMatchEntity.NAME_HOME_TEAM to TEXT,
            FavoriteMatchEntity.NAME_AWAY_TEAM to TEXT,
            FavoriteMatchEntity.SCORE_HOME to TEXT,
            FavoriteMatchEntity.SCORE_AWAY to TEXT,
            FavoriteMatchEntity.DATE_MATCH to TEXT
        )
        db?.createTable(
            FavoriteTeamsEntity.TABLE_FAV_TEAM, true,
            FavoriteTeamsEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeamsEntity.ID_TEAM to TEXT + UNIQUE,
            FavoriteTeamsEntity.LEAGUE to TEXT + UNIQUE,
            FavoriteTeamsEntity.TEAM to TEXT + UNIQUE,
            FavoriteTeamsEntity.BADGE to TEXT + UNIQUE
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatchEntity.TABLE_FAV, true)
        db?.dropTable(FavoriteTeamsEntity.TABLE_FAV_TEAM, true)
    }
}
