package inn.mroyek.submission5kade.data.remote.repository.detailleague

import inn.mroyek.submission5kade.data.remote.model.DetailLeague
import inn.mroyek.submission5kade.network.ApiService
import io.reactivex.Flowable
import javax.inject.Inject

class DetailLeagueRepositoryImpl @Inject constructor(private val service: ApiService) : DetailLeagueRepository {
    override fun getDetailLeague(idLeagues: String): Flowable<List<DetailLeague>> {
        return service.getDetailLeague(id = idLeagues)
            .map { it.detailLeagues }
    }

}