package inn.mroyek.submission5kade.model.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllTeams(
    val id: Long?,
    val idTeam: String?,
    val strLeague: String?,
    val strTeam: String?,
    val strTeamBadge: String?
) : Parcelable