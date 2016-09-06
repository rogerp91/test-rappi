package com.github.rappi.di;


import com.github.rappi.theme.ThemeContract;
import com.github.rappi.theme.ThemePresenter;
import com.github.rappi.themedetail.DetailThemePresenter;
import com.github.rappi.themedetail.ThemeDetailContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roger Patiño on 01/12/2015.
 */
@Module(library = true, complete = false)
public class PresenterModule {

    @Provides
    @Singleton
    ThemeContract.Presenter provideThemePresenter(ThemePresenter presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    ThemeDetailContract.Presenter provideThemeDetailPresenter(DetailThemePresenter presenter) {
        return presenter;
    }
}