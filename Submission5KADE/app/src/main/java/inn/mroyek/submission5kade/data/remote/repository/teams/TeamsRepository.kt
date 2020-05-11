package inn.mroyek.submission5kade.data.remote.repository.teams

import inn.mroyek.submission5kade.data.remote.model.TeamModel
import inn.mroyek.submission5kade.presentation.model.AllTeams
import io.reactivex.Flowable

interface TeamsRepository {
    fun getAllTeams(idLeagues : String) : Flowable<List<AllTeams>>

    fun getSearchTeam(query: String) : Flowable<MutableList<AllTeams>>

    fun getDetailTeam(idTeam: String?) : Flowable<List<TeamModel>>
}