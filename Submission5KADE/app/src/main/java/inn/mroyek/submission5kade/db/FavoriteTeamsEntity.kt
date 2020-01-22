package inn.mroyek.submission5kade.db

import inn.mroyek.submission5kade.model.pojo.AllTeams

data class FavoriteTeamsEntity(
    val id: Long?,
    val idTeam: String?,
    val strLeague: String?,
    val strTeam: String?,
    val strBadge: String?
) {
    companion object {
        const val TABLE_FAV_TEAM: String = "TABLE_FAV_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val LEAGUE: String = "LEAGUE"
        const val TEAM: String = "TEAM"
        const val BADGE: String = "BADGE"
    }
    fun transform(): AllTeams {
        return AllTeams(
            id = this.id,
            idTeam = this.idTeam,
            strLeague = this.strLeague,
            strTeam = this.strTeam,
            strTeamBadge = this.strBadge
        )
    }
}