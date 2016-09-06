package com.github.rappi.di;

import com.github.rappi.executor.Executor;
import com.github.rappi.executor.MainThread;
import com.github.rappi.executor.MainThreadImpl;
import com.github.rappi.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roger Patiño on 30/11/2015.
 */
@Module(library = true)
public class ExecutorModule {

    @Provides
    @Singleton
    Executor provideExecutor(ThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    @Singleton
    MainThread provideMainThread(MainThreadImpl impl) {
        return impl;
    }
}