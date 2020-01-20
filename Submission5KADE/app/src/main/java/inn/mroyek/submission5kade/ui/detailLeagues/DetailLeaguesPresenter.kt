package inn.mroyek.submission5kade.ui.detailLeagues

import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailLeaguesPresenter(
    private val repository: ApiRepository,
    private val backgroundScheduller: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<DetailLeaguesContract>() {
    fun getDetailLeagues(idLeague: String) {
//        callback?.showProgress(true)
        disposable.add(
            repository.getDetailLeague(idLeagues = idLeague)
                ?.subscribeOn(backgroundScheduller)
                ?.observeOn(mainScheduler)
                ?.subscribe({
                    callback?.getDetailLeagues(it?.get(0))
                }, Throwable::printStackTrace)!!
        )
    }
}
