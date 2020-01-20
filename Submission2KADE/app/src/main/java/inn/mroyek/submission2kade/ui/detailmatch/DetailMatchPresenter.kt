package inn.mroyek.submission2kade.ui.detailmatch

import inn.mroyek.submission2kade.base.BasePresenter
import inn.mroyek.submission2kade.common.logD
import inn.mroyek.submission2kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMatchPresenter(
    private val repository: ApiRepository,
    private val backroundScheduler: Scheduler = Schedulers.io(),
    private val mainSchedull: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<DetailMatchContract>() {

    fun getDetailMatch(idMatchEvent: String?) {
        logD("IDNYA", "$idMatchEvent")
            disposable.add(
                repository.getDetailMatchs(idMatch = idMatchEvent)
                    ?.subscribeOn(backroundScheduler)
                    ?.observeOn(mainSchedull)
                    ?.subscribe({
                        callback?.showDetailMatch(it?.get(0))
                    }, {
                        callback?.onFail(it.message!!)
                    })!!
            )

    }

    fun getDetailTeam(idTeamHome: String?, idTeamAway: String?) {
        getTeams(idTeamHome = idTeamHome)
        getTeams(idTeamAway = idTeamAway)
    }

    private fun getTeams(idTeamHome: String? = null, idTeamAway: String? = null) {
        val idTeam = idTeamHome ?: idTeamAway
        disposable.add(
            repository.getDetailTeam(idTeam)
                ?.subscribeOn(backroundScheduler)
                ?.observeOn(mainSchedull)
                ?.subscribe({ listItem ->
                    if (listItem?.isNotEmpty()!!) {
                        idTeamHome?.let {
                            callback?.showTeamHome(listItem[0])
                        }
                        idTeamAway?.let {
                            callback?.showTeamAway(listItem[0])
                        }
                    } else {
                        callback?.onFail("gak ada $idTeam")
                    }
                }, {
                    callback?.onFail(it.message!!)
                })!!
        )
    }
}