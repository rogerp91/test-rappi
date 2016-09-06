package com.github.rappi.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.rappi.RappiApplication;
import com.github.rappi.di.ActivityModule;
import com.github.rappi.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Roger Patiño on 06/09/2016.
 */

public class BaseActivity extends AppCompatActivity{

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    /**
     * Inject
     */
    private void injectDependencies() {
        RappiApplication application = (RappiApplication) getApplication();
        List<Object> activityScopeModules = new ArrayList<>();
        activityScopeModules.add(new ActivityModule(this));
        activityGraph = application.buildGraphWithAditionalModules(activityScopeModules);
        inject(this);
    }

    private void inject(Object entityToGetInjected) {
        activityGraph.inject(entityToGetInjected);
    }

}