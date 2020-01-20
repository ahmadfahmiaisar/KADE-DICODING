package inn.mroyek.submission5kade.ui.detailteams

import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailTeamsPresenter (
    private val repository: ApiRepository,
    private val backgroundScheduler: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<DetailTeamsContract>(){
    fun getTeam(idTeam: String?){
        disposable.add(
            repository.getDetailTeam(idTeam)
                ?.subscribeOn(backgroundScheduler)
                ?.observeOn(mainScheduler)
                ?.subscribe({team ->
                    team.let {
                        callback?.showDetailTeams(team!![0])
                    }
//                    callback?.showDetailTeams()
                }, {
                    callback?.onFail(it.message!!)
                })!!
        )
    }
}