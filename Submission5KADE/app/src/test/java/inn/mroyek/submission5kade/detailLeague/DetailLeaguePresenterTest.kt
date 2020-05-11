package inn.mroyek.submission5kade.detailLeague

import inn.mroyek.submission5kade.data.remote.model.DetailLeague
import inn.mroyek.submission5kade.network.ApiRepository
import inn.mroyek.submission5kade.presentation.ui.detailLeagues.DetailLeaguesContract
import inn.mroyek.submission5kade.presentation.ui.detailLeagues.DetailLeaguesPresenter
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

        presenter.bind(callBack)

        `when`(repository.getDetailLeague(idLeague)).thenReturn(Observable.just(listLeague))
        presenter.getDetailLeagues(idLeague)
        verify(callBack, never()).getDetailLeagues(response)
    }

}