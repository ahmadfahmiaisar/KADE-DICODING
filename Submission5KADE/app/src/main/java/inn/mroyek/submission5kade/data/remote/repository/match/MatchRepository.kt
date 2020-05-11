package inn.mroyek.submission5kade.data.remote.repository.match

import inn.mroyek.submission5kade.data.remote.model.MatchModel
import inn.mroyek.submission5kade.data.remote.model.TeamModel
import inn.mroyek.submission5kade.presentation.model.Matchs
import inn.mroyek.submission5kade.presentation.model.Search
import io.reactivex.Flowable

interface MatchRepository {
    fun getMatch(typeMatch: String, leagueId: String) : Flowable<MutableList<Matchs>>

    fun getSearchMatch(query: String) : Flowable<List<Search>>

    fun getDetailMatch(idMatch: String): Flowable<List<MatchModel>>

    fun getDetailTeam(idTeam: String?) : Flowable<List<TeamModel>>

}