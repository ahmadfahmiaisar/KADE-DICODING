package inn.mroyek.submission5kade.data.remote.repository.Standings

import inn.mroyek.submission5kade.data.remote.model.Table
import io.reactivex.Flowable

interface StandingRepository {
    fun getStandings(idLeagues: String) : Flowable<List<Table>>
}