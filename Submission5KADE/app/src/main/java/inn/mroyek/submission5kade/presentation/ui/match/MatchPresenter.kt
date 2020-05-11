package inn.mroyek.submission5kade.presentation.ui.match

import inn.mroyek.submission5kade.data.remote.repository.match.MatchRepository
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MatchPresenter @Inject constructor(
    private val repo: MatchRepository
) : BasePresenter<MatchContract>() {

    fun getMatch(mathchType: String, leaguesId: String) {
        disposable.add(
            repo.getMatch(mathchType, leaguesId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view?.loadMatchs(listMacth = it)
                }, Throwable::printStackTrace)!!
        )

    }
}