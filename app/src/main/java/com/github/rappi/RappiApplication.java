package com.github.rappi;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;

import com.github.rappi.di.AppModules;

import java.util.List;
import java.util.concurrent.TimeUnit;

import dagger.ObjectGraph;

/**
 * Created by Roger Patiño on 05/09/2016.
 */

public class RappiApplication extends Application {

    private ObjectGraph objectGraph;

    private static RappiApplication instance;

    public static RappiApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDependencyInjection();
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(1));//Splash
    }

    public ObjectGraph buildGraphWithAditionalModules(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException("Null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }

    public void initDependencyInjection() {
        objectGraph = ObjectGraph.create(new AppModules(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

}