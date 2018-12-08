package clean.movies.main.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProviderImpl @Inject constructor(): SchedulerProvider {

    companion object {
        private val io = Schedulers.io()
        private val main = AndroidSchedulers.mainThread()
    }

    override fun <T> ioToMainObservable(): ObservableTransformer<T, T> = ObservableTransformer { upstream ->
        upstream.subscribeOn(io)
                .observeOn(main)
    }

    override fun <T> ioToMainSingle(): SingleTransformer<T, T> = SingleTransformer { upstream ->
        upstream.subscribeOn(io)
                .observeOn(main)
    }


    override fun ioToMainCompletable(): CompletableTransformer = CompletableTransformer { upstream ->
        upstream.subscribeOn(io)
                .observeOn(main)
    }


    override fun <T> ioToMainFlowable(): FlowableTransformer<T, T> = FlowableTransformer { upstream ->
        upstream.subscribeOn(io)
                .observeOn(main)
    }


    override fun <T> ioToMainMaybe(): MaybeTransformer<T, T> = MaybeTransformer { upstream ->
        upstream.subscribeOn(io)
                .observeOn(main)
    }
}

interface SchedulerProvider {

    fun <T> ioToMainObservable(): ObservableTransformer<T, T>

    fun <T> ioToMainSingle(): SingleTransformer<T, T>

    fun ioToMainCompletable(): CompletableTransformer

    fun <T> ioToMainFlowable(): FlowableTransformer<T, T>

    fun <T> ioToMainMaybe(): MaybeTransformer<T, T>
}