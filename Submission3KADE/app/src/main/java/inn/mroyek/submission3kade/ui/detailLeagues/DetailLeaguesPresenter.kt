package inn.mroyek.submission3kade.ui.detailLeagues

import inn.mroyek.submission3kade.base.BasePresenter
import inn.mroyek.submission3kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailLeaguesPresenter(
    private val repository: ApiRepository,
    private val backgroundScheduller: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
)  : BasePresenter<DetailLeaguesContract>() {
    fun getDetailLeagues(idLeague: String){
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
