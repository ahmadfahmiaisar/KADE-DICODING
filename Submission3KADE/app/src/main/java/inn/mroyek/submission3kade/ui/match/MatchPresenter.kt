package inn.mroyek.submission3kade.ui.match

import inn.mroyek.submission3kade.base.BasePresenter
import inn.mroyek.submission3kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchPresenter(
    private val repo: ApiRepository,
    private val backgroundSchedull: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<MatchContract>() {

    fun getMatch(mathchType: String?, leaguesId: String) {
        disposable.add(
            repo.getMatchs(mathchType, leaguesId)
                ?.subscribeOn(backgroundSchedull)
                ?.observeOn(mainScheduler)
                ?.subscribe({
                    callback?.loadMatchs(listMacth = it)
                }, Throwable::printStackTrace)!!
        )

    }
}