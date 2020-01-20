package inn.mroyek.submission3kade.model.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Matchs(
    val id: Long?,
    val eventId: String?,
    val homeTeamId: String?,
    val awayTeamId: String?,
    val homeTeamName: String?,
    val awayTeamName: String?,
    val homeScore: String?,
    val awayScore: String?,
    val matchDate: String?
) :Parcelable