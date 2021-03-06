package inn.mroyek.submission5kade.network

import inn.mroyek.submission5kade.data.remote.model.DetailLeague
import inn.mroyek.submission5kade.data.remote.model.MatchModel
import inn.mroyek.submission5kade.data.remote.model.Table
import inn.mroyek.submission5kade.data.remote.model.TeamModel
import inn.mroyek.submission5kade.presentation.model.AllTeams
import inn.mroyek.submission5kade.presentation.model.Matchs
import inn.mroyek.submission5kade.presentation.model.Search
import io.reactivex.Observable
import io.reactivex.Single

/*

class ApiRepository {
    private val apiService: ApiService by lazy {
        NetworkConfig.getApiService()
    }

    fun getMatchs(typeMatch: String?, leagueId: String): Single<MutableList<Matchs?>>? {
        return apiService.getMatch(typeMatch, leagueId)
            .flatMapIterable {
                it.events
            }
            .map {
                it.transforms()
            }
            .toList()
    }


    fun getDetailMatchs(idMatch: String?): Observable<List<MatchModel?>?>? {
        return apiService.getMatchDetail(idMatch)
            .map { it.events }
    }

    fun getDetailTeam(idTeam: String?): Observable<List<TeamModel?>?>? {
        return apiService.getDetailTeam(idTeam)
            .map { it.teams }
    }

    fun getSearchMatch(query: String?): Single<MutableList<Search>>? {
        return apiService.getSearchMatch(query = query)
            .flatMapIterable { searchMatchResponse ->
                searchMatchResponse.eventSearch?.filter { it?.strSport.equals("Soccer") }
            }
            .map {
                it.transfromSearch()
            }
            .toList()
    }
    fun getSearchTeam(querys: String?): Single<MutableList<AllTeams>>? {
        return apiService.getSearchTeams(query = querys)
            .flatMapIterable {
                it.teams
            }
            .map {
                it.transformSearhTeam()
            }
            .toList()
    }

    fun getDetailLeague(idLeagues: String): Observable<List<DetailLeague?>?>? {
        return apiService.getDetailLeague(id = idLeagues)
            .map { it.detailLeagues }
    }

    fun getAllTeams(idLeagues: String): Single<MutableList<AllTeams>> {
        return apiService.getAllTeams(id = idLeagues)
            .flatMapIterable {
                it.teams
            }
            .map {
                it.transformTeam()
            }
            .toList()
    }

    fun getStandings(idLeagues: String): Observable<List<Table?>?>? {
        return apiService.getStanding(idLeague = idLeagues )
            .map { it.table }
    }
}
*/
