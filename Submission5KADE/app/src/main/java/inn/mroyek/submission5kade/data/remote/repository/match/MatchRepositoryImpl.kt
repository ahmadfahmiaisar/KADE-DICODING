package inn.mroyek.submission5kade.data.remote.repository.match

import inn.mroyek.submission5kade.data.remote.model.MatchModel
import inn.mroyek.submission5kade.data.remote.model.TeamModel
import inn.mroyek.submission5kade.network.ApiService
import inn.mroyek.submission5kade.presentation.model.Matchs
import inn.mroyek.submission5kade.presentation.model.Search
import io.reactivex.Flowable
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(private val service: ApiService) : MatchRepository {

    override fun getMatch(typeMatch: String, leagueId: String): Flowable<MutableList<Matchs>> {
        return service.getMatch(typeMatch, leagueId)
            .flatMap { Flowable.fromIterable(it.events) }
            .map { it.transforms() }
            .toList()
            .toFlowable()
    }

    override fun getSearchMatch(querys: String): Flowable<List<Search>> {
        return service.getSearchMatch(query = querys)
            .flatMap { Flowable.fromIterable(it.eventSearch?.filter { eventSearch -> eventSearch?.strSport.equals("Soccer") }) }
            .map { it.transfromSearch() }
            .toList()
            .toFlowable()
    }

    override fun getDetailMatch(idMatch: String): Flowable<List<MatchModel>> {
        return service.getMatchDetail(idMatch)
            .map { it.events }
    }

    override fun getDetailTeam(idTeam: String?): Flowable<List<TeamModel>> {
        return service.getDetailTeam(idTeam = idTeam)
            .map { it.teams }
    }
}