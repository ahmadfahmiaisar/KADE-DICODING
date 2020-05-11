package inn.mroyek.submission5kade.data.remote.repository.Standings

import inn.mroyek.submission5kade.data.remote.model.Table
import inn.mroyek.submission5kade.network.ApiService
import io.reactivex.Flowable
import javax.inject.Inject

class StandingRepositoryImpl @Inject constructor(private val service: ApiService) : StandingRepository {
    override fun getStandings(idLeagues: String): Flowable<List<Table>> {
        return service.getStanding(idLeague = idLeagues)
            .map { it.table }
    }
}