package inn.mroyek.submission4kade.model.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search(
    val id: Long?,
    val sportStr: String?,
    val eventId: String?,
    val homeTeamId: String?,
    val awayTeamId: String?,
    val homeTeamStr: String?,
    val awayTeamStr: String?,
    val homeScoreInt: String?,
    val awayScoreInt: String?,
    val dateEvent: String?
) : Parcelable