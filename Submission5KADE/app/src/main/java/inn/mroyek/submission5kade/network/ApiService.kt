package inn.mroyek.submission5kade.network

import inn.mroyek.submission5kade.common.*
import inn.mroyek.submission5kade.model.response.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/json/1/{typeMatch}")
    fun getMatch(@Path("typeMatch") typeMatch: String?, @Query("id") id: String?): Observable<MatchResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String?) : Observable<MatchResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") idTeam: String?) : Observable<TeamResponse>

    @GET(ENDPOINT_DETAIL_LEAGUES)
    fun getDetailLeague(@Query("id") id: String): Observable<DetailLeagueResponse>

    @GET("/api/v1/json/1/searchevents.php")
    fun getSearchMatch(@Query("e") query: String?): Observable<SearchMatchResponse>

    @GET("/api/v1/json/1/lookup_all_teams.php")
    fun getAllTeams(@Query("id") id: String?): Observable<AllTeamResponse>
}