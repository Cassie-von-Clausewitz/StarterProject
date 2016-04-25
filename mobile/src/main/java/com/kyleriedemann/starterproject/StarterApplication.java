package com.kyleriedemann.starterproject;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Extension of activity that handles installing leak canary, and adding Timber if running in debug
 *
 * Created by kyle on 4/22/16.
 * @author kylealanr
 */
public class StarterApplication extends Application {
    private static StarterApplication starterApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

        starterApplication = this;
    }

    public static StarterApplication instance(){
        return starterApplication;
    }
}
