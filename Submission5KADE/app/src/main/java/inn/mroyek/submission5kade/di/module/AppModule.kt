package inn.mroyek.submission5kade.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import inn.mroyek.submission5kade.BaseApp
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideAppContext(app: BaseApp) : Context = app.applicationContext
}