package inn.mroyek.submission5kade.presentation.ui.searchTeam

import inn.mroyek.submission5kade.data.remote.repository.teams.TeamsRepository
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchTeamPresenter @Inject constructor(
    private val repository: TeamsRepository
) : BasePresenter<SearchTeamContract>() {
    fun getSearchTeam(query: String) {
        disposable.add(
            repository.getSearchTeam(query)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view?.showTeam(listTeam = it)
                }, Throwable::printStackTrace)!!
        )
    }
}