package inn.mroyek.submission5kade.ui.detailteams

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.response.TeamModel

interface DetailTeamsContract : BaseServiceCallBack {
    fun showDetailTeams(team: TeamModel?)
    fun onFail(msg: String)
    //favorite
    fun saveFavorite()
    fun removeFavorite()
    fun saveExisFavorite(saved: Boolean)
}