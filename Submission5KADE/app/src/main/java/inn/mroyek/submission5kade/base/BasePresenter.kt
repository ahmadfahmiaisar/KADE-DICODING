package inn.mroyek.submission5kade.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseServiceCallBack> {
    protected var callback: T? = null
    protected val disposable = CompositeDisposable()

    fun bindCallBack(callBack: T?) {
        this.callback = callBack
    }

    fun unBind() {
        this.callback = null
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}