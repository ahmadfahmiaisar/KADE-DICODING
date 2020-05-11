package inn.mroyek.submission5kade.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class StandingsResponse(
    val table: List<Table>
)
@Parcelize
data class Table(
    val draw: Int?,
    val goalsagainst: Int?,
    val goalsdifference: Int?,
    val goalsfor: Int?,
    val loss: Int?,
    val name: String?,
    val played: Int?,
    val teamid: String?,
    val total: Int?,
    val win: Int?
) : Parcelable