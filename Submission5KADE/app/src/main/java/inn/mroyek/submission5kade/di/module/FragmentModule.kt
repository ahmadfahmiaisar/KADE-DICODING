package inn.mroyek.submission5kade.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import inn.mroyek.submission5kade.presentation.ui.main.MainMatchFootballFragment
import inn.mroyek.submission5kade.presentation.ui.match.NextMatchFragment
import inn.mroyek.submission5kade.presentation.ui.match.PrevMatchFragment
import inn.mroyek.submission5kade.presentation.ui.standings.StandingFragment
import inn.mroyek.submission5kade.presentation.ui.team.AllTeamsFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun mainMatchFootballFragment(): MainMatchFootballFragment

    @ContributesAndroidInjector
    abstract fun prevMatchFragment() : PrevMatchFragment

    @ContributesAndroidInjector
    abstract fun nextMatchFragment() : NextMatchFragment

    @ContributesAndroidInjector
    abstract fun standingFragment() : StandingFragment

    @ContributesAndroidInjector
    abstract fun allTeamFragment() : AllTeamsFragment

    //TODO belum semua fragment terdaftar
}
