package clean.movies.main

interface BasePresenter<V : BaseView, I: BaseInteractor> {

    fun onAttach(view: V?)

    fun onDetach()
}