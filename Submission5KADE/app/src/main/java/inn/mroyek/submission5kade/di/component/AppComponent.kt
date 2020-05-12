package inn.mroyek.submission5kade.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import inn.mroyek.submission5kade.BaseApp
import inn.mroyek.submission5kade.di.module.*
import inn.mroyek.submission5kade.presentation.ui.main.MainMatchFootballFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityModule::class,
        RepositoryModule::class,
        FragmentModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(baseApp: BaseApp)
    fun inject(mainBot: MainMatchFootballFragment)
}