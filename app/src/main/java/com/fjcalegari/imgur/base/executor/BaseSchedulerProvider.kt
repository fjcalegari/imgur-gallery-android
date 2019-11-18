package com.fjcalegari.imgur.base.executor

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

}
