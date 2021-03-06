package inn.mroyek.submission5kade.matchDetail

import inn.mroyek.submission5kade.data.remote.model.MatchModel
import inn.mroyek.submission5kade.network.ApiRepository
import inn.mroyek.submission5kade.presentation.ui.detailmatch.DetailMatchContract
import inn.mroyek.submission5kade.presentation.ui.detailmatch.DetailMatchPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {
    @Mock
    lateinit var repository: ApiRepository
    @Mock
    lateinit var callBack: DetailMatchContract
    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        val testScheduler = TestScheduler()
        presenter = DetailMatchPresenter(repository, testScheduler, testScheduler)
    }

    @Test
    fun `fetch valid data`(){
        val listMatch = emptyList<MatchModel>()
        val idMatch = "584435"
        val model: MatchModel? = null

        presenter.bind(callBack)

        `when`(repository.getDetailMatchs(idMatch)).thenReturn(Observable.just(listMatch))
        presenter.getDetailMatch(idMatch)
        verify(callBack, never()).showDetailMatch(model)
    }

    @Test
    fun `fetch error`(){
        val exception = Exception()
        val idMatch = "584435"

        presenter.bind(callBack)
        `when`(repository.getDetailMatchs(idMatch)).thenReturn(Observable.error(exception))
        presenter.getDetailMatch(idMatch)
        verify(callBack, never()).onFail("error")
    }

}