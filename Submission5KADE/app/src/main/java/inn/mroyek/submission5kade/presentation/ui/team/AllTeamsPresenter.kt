package inn.mroyek.submission5kade.presentation.ui.team

import inn.mroyek.submission5kade.data.remote.repository.teams.TeamsRepository
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllTeamsPresenter @Inject constructor(
    private val repository: TeamsRepository
) : BasePresenter<AllTeamsContract>() {
    fun getTeam(idLeague: String) {
        disposable.add(
            repository.getAllTeams(idLeague)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.getTeams(listTeams = it)
                }, Throwable::printStackTrace)
        )
    }
}