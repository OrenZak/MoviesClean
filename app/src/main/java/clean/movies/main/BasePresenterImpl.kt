package clean.movies.main

import clean.movies.main.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BasePresenterImpl<V : BaseView, I : BaseInteractor>
@Inject internal constructor(protected var interactor: I?,
                             protected val schedulerProvider: SchedulerProvider,
                             protected val compositeDisposable: CompositeDisposable) : BasePresenter<V, I> {

    protected var view: V? = null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        interactor = null
        view = null
    }

}