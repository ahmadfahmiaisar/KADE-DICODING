package inn.mroyek.submission5kade.match

import inn.mroyek.submission5kade.presentation.model.Matchs
import inn.mroyek.submission5kade.network.ApiRepository
import inn.mroyek.submission5kade.presentation.ui.match.MatchContract
import inn.mroyek.submission5kade.presentation.ui.match.MatchPresenter
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest {
    @Mock
    lateinit var repository: ApiRepository
    @Mock
    lateinit var callback: MatchContract
    private lateinit var presenter: MatchPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        val testScheduler = TestScheduler()
        presenter = MatchPresenter(repository, testScheduler, testScheduler)
    }

    @Test
    fun `fetch valid data`(){
        val listMatch = mutableListOf<Matchs?>()
        val typeMatch = "eventspastleague.php"
        val leagudId = "4335"

        presenter.bind(callback)

        Mockito.`when`(repository.getMatchs(typeMatch, leagudId))
            .thenReturn(Single.just(listMatch))

        presenter.getMatch(typeMatch, leagudId)

        verify(callback, never()).loadMatchs(listMatch)
    }
}