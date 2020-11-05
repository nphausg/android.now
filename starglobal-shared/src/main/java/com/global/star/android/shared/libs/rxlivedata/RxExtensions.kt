package com.global.star.android.shared.libs.rxlivedata

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Safely dispose = if not null and not already disposed
 */
fun Disposable?.safeDispose() {
    if (this?.isDisposed == false) {
        dispose()
    }
}


fun <T> Observable<T>.applyScheduler(scheduler: Scheduler): Observable<T> =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun <T> applyObservableIoScheduler(): ObservableTransformer<T, T> {
    return ObservableTransformer { observable -> observable.applyScheduler(Schedulers.io()) }
}

private fun <T> Flowable<T>.applyScheduler(scheduler: Scheduler): Flowable<T> =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun <T> applyFlowableIoScheduler(): FlowableTransformer<T, T> {
    return FlowableTransformer { flowable -> flowable.applyScheduler(Schedulers.io()) }
}

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler): Single<T> =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun <T> applySingleIoScheduler(): SingleTransformer<T, T> {
    return SingleTransformer { single -> single.applyScheduler(Schedulers.io()) }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) =
    compositeDisposable.add(this)

fun Throwable?.isNetworkException(): Boolean = (this is RuntimeException &&
        (cause is UnknownHostException || cause is SocketTimeoutException)) ||
        this is UnknownHostException ||
        this is SocketTimeoutException