package inn.mroyek.submission5kade.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import inn.mroyek.submission5kade.presentation.ui.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    //TODO belum semua activity terdaftar
}