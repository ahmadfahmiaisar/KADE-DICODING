package inn.mroyek.submission5kade.data.remote.repository.detailleague

import inn.mroyek.submission5kade.data.remote.model.DetailLeague
import io.reactivex.Flowable

interface DetailLeagueRepository {
    fun getDetailLeague(idLeagues: String) : Flowable<List<DetailLeague>>
}