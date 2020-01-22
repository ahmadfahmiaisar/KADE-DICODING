package inn.mroyek.submission5kade.ui.searchTeam

import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchTeamPresenter(
    private val repository: ApiRepository,
    private val backgroundScheduler: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<SearchTeamContract>() {
    fun getSearchTeam(query: String) {
        disposable.add(
            repository.getSearchTeam(query)
                ?.subscribeOn(backgroundScheduler)
                ?.observeOn(mainScheduler)
                ?.subscribe({
                    callback?.showTeam(listTeam = it)
                }, Throwable::printStackTrace)!!
        )
    }
}