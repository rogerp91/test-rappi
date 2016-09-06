package com.github.rappi.di;

import com.github.rappi.data.source.local.ThemeLocalDataSource;
import com.github.rappi.data.source.remote.ThemeRemoteDataSource;
import com.github.rappi.theme.ThemeContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roger Patiño on 11/07/2016.
 */
@Module(complete = false, library = true)
public class DataSourceModule {

    @Provides
    @Singleton
    ThemeContract.ThemeLocalDataSource provideLocalDataSourceTheme(ThemeLocalDataSource dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    ThemeContract.ThemeRemoteDataSource provideRemoteDataSourceTheme(ThemeRemoteDataSource dataSource) {
        return dataSource;
    }

}