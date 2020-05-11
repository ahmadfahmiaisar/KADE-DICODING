package inn.mroyek.submission5kade.network

import inn.mroyek.submission5kade.common.ENDPOINT_DETAIL_LEAGUES
import inn.mroyek.submission5kade.data.remote.model.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/json/1/{typeMatch}")
    fun getMatch(@Path("typeMatch") typeMatch: String?, @Query("id") id: String?): Flowable<MatchResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String?) : Flowable<MatchResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") idTeam: String?) : Flowable<TeamResponse>

    @GET(ENDPOINT_DETAIL_LEAGUES)
    fun getDetailLeague(@Query("id") id: String): Flowable<DetailLeagueResponse>

    @GET("/api/v1/json/1/searchevents.php")
    fun getSearchMatch(@Query("e") query: String?): Flowable<SearchMatchResponse>

    @GET("/api/v1/json/1/lookup_all_teams.php")
    fun getAllTeams(@Query("id") id: String?): Flowable<AllTeamResponse>

    @GET("/api/v1/json/1/searchteams.php")
    fun getSearchTeams(@Query("t") query: String?) : Flowable<SearchTeamResponse>

    @GET("/api/v1/json/1/lookuptable.php")
    fun getStanding(@Query("l") idLeague: String?) : Flowable<StandingsResponse>
}