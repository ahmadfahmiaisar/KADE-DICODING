package inn.mroyek.submission4kade.detailLeague

import inn.mroyek.submission4kade.model.response.DetailLeague
import inn.mroyek.submission4kade.network.ApiRepository
import inn.mroyek.submission4kade.ui.detailLeagues.DetailLeaguesContract
import inn.mroyek.submission4kade.ui.detailLeagues.DetailLeaguesPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DetailLeaguePresenterTest {
    @Mock
    lateinit var repository: ApiRepository
    @Mock
    lateinit var callBack: DetailLeaguesContract
    private lateinit var presenter: DetailLeaguesPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        val testScheduler = TestScheduler()
        presenter = DetailLeaguesPresenter(repository, testScheduler, testScheduler)
    }

    @Test
    fun `fetch valid data`(){
        val listLeague = emptyList<DetailLeague>()
        val idLeague = "4328"
        val response: DetailLeague? = null

        presenter.bindCallBack(callBack)

        `when`(repository.getDetailLeague(idLeague)).thenReturn(Observable.just(listLeague))
        presenter.getDetailLeagues(idLeague)
        verify(callBack, never()).getDetailLeagues(response)
    }

}