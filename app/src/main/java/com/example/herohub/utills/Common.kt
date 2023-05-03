package com.example.herohub.utills

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Aziza Helmy on 5/3/2023.
 */

@SuppressLint("CheckResult")
fun <T : Any> Single<T>.handleThreadsAndSubscribe(
    onSuccess: (data: T) -> Unit,
    onFailed: (e: Throwable) -> Unit
): Disposable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onSuccess, onFailed)
}