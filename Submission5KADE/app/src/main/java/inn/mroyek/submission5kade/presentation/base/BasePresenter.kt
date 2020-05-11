package inn.mroyek.submission5kade.presentation.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseServiceCallBack> {
    protected var view: T? = null
    protected val disposable = CompositeDisposable()

    fun bind(view: T?) {
        this.view = view
    }

    fun unbind() {
        this.view = null
        if (!disposable.isDisposed)
            disposable.dispose()
    }

    fun destroy() {
        if (!disposable.isDisposed)
            disposable.dispose()
        unbind()
    }
}