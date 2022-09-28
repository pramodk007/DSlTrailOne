package com.example.movieexp.core.base.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
//        log("onCreate()")
    }

    override fun onActivityResumed(activity: Activity) {
//        log("onResume()")
    }

    override fun onActivityPaused(activity: Activity) {
//        log("onPause()")
    }

    override fun onActivityDestroyed(activity: Activity) {
//        log("onDestroy()")
    }

    override fun onActivityStarted(activity: Activity) {
        // no-op
    }

    override fun onActivityStopped(activity: Activity) {
        // no-op
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // no-op
    }
}