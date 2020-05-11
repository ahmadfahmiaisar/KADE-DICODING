package inn.mroyek.submission5kade.presentation.ui.detailteams

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.data.remote.model.TeamModel

interface DetailTeamsContract : BaseServiceCallBack {
    fun showDetailTeams(team: TeamModel?)
    fun onFail(msg: String)
    //favorite
    fun saveFavorite()
    fun removeFavorite()
    fun saveExisFavorite(saved: Boolean)
}