package inn.mroyek.submission5kade.ui.team

import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllTeamsPresenter(
    private val repository: ApiRepository,
    private val backgroundScheduler: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<AllTeamsContract>() {
    fun getTeam(idLeague: String) {
        disposable.add(
            repository.getAllTeams(idLeague)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    callback?.getTeams(listTeams = it)
                }, Throwable::printStackTrace)
        )
    }
}