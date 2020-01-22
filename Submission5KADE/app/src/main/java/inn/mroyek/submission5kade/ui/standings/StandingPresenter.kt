package inn.mroyek.submission5kade.ui.standings

import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StandingPresenter(
    private val repository: ApiRepository,
    private val backgroundScheduler: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<StandingContract>() {
    fun getStanding(id: String) {
        disposable.add(
            repository.getStandings(id)
                ?.subscribeOn(backgroundScheduler)
                ?.observeOn(mainScheduler)
                ?.subscribe({
                    callback?.showStanding(standing = it!!)
                }, Throwable::printStackTrace)!!
        )
    }
}