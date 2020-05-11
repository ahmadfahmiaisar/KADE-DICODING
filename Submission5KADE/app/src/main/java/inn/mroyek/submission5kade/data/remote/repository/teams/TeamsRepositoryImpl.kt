package inn.mroyek.submission5kade.data.remote.repository.teams

import inn.mroyek.submission5kade.data.remote.model.TeamModel
import inn.mroyek.submission5kade.network.ApiService
import inn.mroyek.submission5kade.presentation.model.AllTeams
import io.reactivex.Flowable
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(private val service: ApiService) : TeamsRepository {

    override fun getSearchTeam(query: String): Flowable<MutableList<AllTeams>> {
        return service.getSearchTeams(query = query)
            .flatMap { Flowable.fromIterable(it.teams) }
            .map { it.transformSearhTeam() }
            .toList()
            .toFlowable()
    }

    override fun getAllTeams(idLeagues: String): Flowable<List<AllTeams>> {
        return service.getAllTeams(id = idLeagues)
            .flatMap { Flowable.fromIterable(it.teams) }
            .map { it.transformTeam() }
            .toList()
            .toFlowable()

    }

    override fun getDetailTeam(idTeam: String?): Flowable<List<TeamModel>> {
        return service.getDetailTeam(idTeam = idTeam)
            .map { it.teams }
    }
}