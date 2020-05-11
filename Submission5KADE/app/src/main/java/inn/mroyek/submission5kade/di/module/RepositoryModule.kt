package inn.mroyek.submission5kade.di.module

import dagger.Module
import dagger.Provides
import inn.mroyek.submission5kade.data.remote.repository.Standings.StandingRepository
import inn.mroyek.submission5kade.data.remote.repository.Standings.StandingRepositoryImpl
import inn.mroyek.submission5kade.data.remote.repository.detailleague.DetailLeagueRepository
import inn.mroyek.submission5kade.data.remote.repository.detailleague.DetailLeagueRepositoryImpl
import inn.mroyek.submission5kade.data.remote.repository.match.MatchRepository
import inn.mroyek.submission5kade.data.remote.repository.match.MatchRepositoryImpl
import inn.mroyek.submission5kade.data.remote.repository.teams.TeamsRepository
import inn.mroyek.submission5kade.data.remote.repository.teams.TeamsRepositoryImpl
import inn.mroyek.submission5kade.network.ApiService
import javax.inject.Singleton

@Module
object RepositoryModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideLeaguesRepository(repositoryImpl: DetailLeagueRepositoryImpl) : DetailLeagueRepository = repositoryImpl

    @JvmStatic
    @Provides
    @Singleton
    fun provideMatchRepository(repositoryImpl: MatchRepositoryImpl) : MatchRepository = repositoryImpl

    @JvmStatic
    @Provides
    @Singleton
    fun provideTeamRepository(repositoryImpl: TeamsRepositoryImpl) : TeamsRepository = repositoryImpl

    @JvmStatic
    @Provides
    @Singleton
    fun provideStandingRepository(repositoryImpl: StandingRepositoryImpl) : StandingRepository = repositoryImpl
}