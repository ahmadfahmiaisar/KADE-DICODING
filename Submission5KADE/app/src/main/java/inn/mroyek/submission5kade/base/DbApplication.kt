package inn.mroyek.submission5kade.base

import android.app.Application
import inn.mroyek.submission5kade.db.DbOpenHelper


class DbApplication : Application() {
    companion object {
        var database: DbOpenHelper? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =
            DbOpenHelper.getInstance(
                applicationContext
            )
    }
}