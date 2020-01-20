package inn.mroyek.submission3kade.db

import inn.mroyek.submission3kade.model.pojo.Matchs

data class FavoriteEntity(val id: Long?,
                          val idMatch: String?,
                          val idHomeTeam: String?,
                          val idAwayTeam: String?,
                          val nameHomeTeam: String?,
                          val nameAwayTeam: String?,
                          val scoreHome: String?,
                          val scoreAway: String?,
                          val dateMatch: String?){
    companion object {
        const val TABLE_FAV: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_MATCH: String = "ID_MATCH"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val NAME_HOME_TEAM: String = "NAME_HOME_TEAM"
        const val NAME_AWAY_TEAM: String = "NAME_AWAY_TEAM"
        const val SCORE_HOME: String = "SCORE_HOME"
        const val SCORE_AWAY: String = "SCORE_AWAY"
        const val DATE_MATCH: String = "DATE_MATCH"
    }
    fun transform(): Matchs {
        return Matchs(
            id = this.id,
            eventId = this.idMatch,
            homeTeamId = this.idHomeTeam,
            awayTeamId = this.idAwayTeam,
            homeTeamName = this.nameHomeTeam,
            awayTeamName = this.nameAwayTeam,
            homeScore = this.scoreHome,
            awayScore = this.scoreAway,
            matchDate = this.dateMatch
        )
    }
}