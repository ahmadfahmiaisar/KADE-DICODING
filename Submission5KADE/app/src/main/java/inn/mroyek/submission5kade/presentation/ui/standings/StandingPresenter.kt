package inn.mroyek.submission5kade.presentation.ui.standings

import inn.mroyek.submission5kade.data.remote.repository.Standings.StandingRepository
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StandingPresenter @Inject constructor(
    private val repository: StandingRepository
) : BasePresenter<StandingContract>() {
    fun getStanding(id: String) {
        disposable.add(
            repository.getStandings(id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view?.showStanding(standing = it!!)
                }, Throwable::printStackTrace)!!
        )
    }
}