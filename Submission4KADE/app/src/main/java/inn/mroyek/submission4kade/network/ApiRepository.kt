package inn.mroyek.submission4kade.network

import inn.mroyek.submission4kade.model.response.DetailLeague
import inn.mroyek.submission4kade.model.response.MatchModel
import inn.mroyek.submission4kade.model.response.TeamModel
import inn.mroyek.submission4kade.model.pojo.Matchs
import inn.mroyek.submission4kade.model.pojo.Search
import io.reactivex.Observable
import io.reactivex.Single


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

    fun getDetailLeague(idLeagues: String): Observable<List<DetailLeague?>?>? {
        return apiService.getDetailLeague(id = idLeagues)
            .map { it.detailLeagues }
    }

}
