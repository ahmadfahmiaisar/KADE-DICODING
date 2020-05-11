package inn.mroyek.submission5kade.presentation.ui.searchMatch

import inn.mroyek.submission5kade.data.remote.repository.match.MatchRepository
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchMatchPresenter @Inject constructor(
    private val repository: MatchRepository
) : BasePresenter<SearchMatchContract>() {
    fun getSearchMatch(query: String) {
        disposable.add(
            repository.getSearchMatch(query)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view?.showMatch(listMacth = it)
                }, Throwable::printStackTrace)!!

        )
    }
}