package com.github.rappi.di;

import com.github.rappi.RappiApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roger Patiño on 06/01/2016.
 */
@Module(
        injects = RappiApplication.class,
        library = true,
        includes = {
                ExecutorModule.class
        }
)
public class AppModules {

    public RappiApplication app;

    public AppModules(RappiApplication app) {
        this.app = app;
    }

    @Provides
    RappiApplication provideApplication() {
        return this.app;
    }
}