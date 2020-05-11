package inn.mroyek.submission5kade.presentation.base

import android.app.Application
import inn.mroyek.submission5kade.data.local.DbOpenHelper


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