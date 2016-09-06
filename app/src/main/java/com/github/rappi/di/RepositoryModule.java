package com.github.rappi.di;

import com.github.rappi.data.source.ThemeRepository;
import com.github.rappi.theme.ThemeContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roger Patiño on 11/07/2016.
 */
@Module(complete = false, library = true)
public class RepositoryModule {

    @Provides
    @Singleton
    ThemeContract.Repository provideRepositoryRepository(ThemeRepository repository) {
        return repository;
    }

}