package inn.mroyek.submission5kade.model.response

import android.os.Parcelable
import inn.mroyek.submission5kade.model.pojo.AllTeams
import kotlinx.android.parcel.Parcelize

data class SearchTeamResponse(
    val teams: List<SearchTeam?>?
)
@Parcelize
data class SearchTeam(
    val idAPIfootball: String?,
    val idLeague: String?,
    val idSoccerXML: String?,
    val idTeam: String?,
    val intFormedYear: String?,
    val intLoved: String?,
    val intStadiumCapacity: String?,
    val strAlternate: String?,
    val strCountry: String?,
    val strDescriptionCN: String?,
    val strDescriptionDE: String?,
    val strDescriptionEN: String?,
    val strDescriptionES: String?,
    val strDescriptionFR: String?,
    val strDescriptionHU: String?,
    val strDescriptionIL: String?,
    val strDescriptionIT: String?,
    val strDescriptionJP: String?,
    val strDescriptionNL: String?,
    val strDescriptionNO: String?,
    val strDescriptionPL: String?,
    val strDescriptionPT: String?,
    val strDescriptionRU: String?,
    val strDescriptionSE: String?,
    val strDivision: String?,
    val strFacebook: String?,
    val strGender: String?,
    val strInstagram: String?,
    val strKeywords: String?,
    val strLeague: String?,
    val strLocked: String?,
    val strManager: String?,
    val strRSS: String?,
    val strSport: String?,
    val strStadium: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val strStadiumThumb: String?,
    val strTeam: String?,
    val strTeamBadge: String?,
    val strTeamBanner: String?,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
    val strTeamShort: String?,
    val strTwitter: String?,
    val strWebsite: String?,
    val strYoutube: String?
) : Parcelable {
    fun transformSearhTeam() : AllTeams {
        return AllTeams(
            id = null,
            idTeam = this.idTeam,
            strLeague = this.strLeague,
            strTeam = this.strTeam,
            strTeamBadge = this.strTeamBadge
        )
    }
}
