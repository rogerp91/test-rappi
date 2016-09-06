package com.github.rappi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.github.rappi.RappiApplication;
import com.github.rappi.di.FragmentModule;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;
import icepick.Icepick;

/**
 * Created by Roger Patiño on 06/09/2016.
 */

public class BaseFragment extends Fragment {

    private ObjectGraph activityGraph;
    protected ActionBar ab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        ab = appCompatActivity.getSupportActionBar();
        injectDependencies();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState); // Landscape o portrait se pierde el contexto
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);// Landscape o portrait se pierde el contexto
        setRetainInstance(true);
    }

    private void injectDependencies() {
        RappiApplication app = (RappiApplication) getActivity().getApplication();
        List<Object> activityScopeModules = new ArrayList<>();
        activityScopeModules.add(new FragmentModule(getActivity()));
        activityGraph = app.buildGraphWithAditionalModules(activityScopeModules);
        inject(this);
    }

    private void inject(Object entityToGetInjected) {
        activityGraph.inject(entityToGetInjected);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

}