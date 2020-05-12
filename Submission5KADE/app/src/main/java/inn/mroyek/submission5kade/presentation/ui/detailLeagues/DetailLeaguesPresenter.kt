package inn.mroyek.submission5kade.presentation.ui.detailLeagues

import inn.mroyek.submission5kade.data.remote.repository.detailleague.DetailLeagueRepository
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailLeaguesPresenter @Inject constructor(private val repository: DetailLeagueRepository) :
    BasePresenter<DetailLeaguesContract>() {

    fun getDetailLeagues(idLeague: String) {
//        callback?.showProgress(true)
        disposable.add(
            repository.getDetailLeague(idLeagues = idLeague)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view?.getDetailLeagues(it?.get(0))
                }, Throwable::printStackTrace)!!
        )
    }
}
/*
,
private val backgroundScheduller: Scheduler = Schedulers.io(),
private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()*/
