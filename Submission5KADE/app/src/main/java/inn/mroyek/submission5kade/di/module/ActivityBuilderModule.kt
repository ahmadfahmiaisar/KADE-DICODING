package inn.mroyek.submission5kade.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import inn.mroyek.submission5kade.presentation.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}