package com.example.samplecode.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ContextModule {

    @Binds
    abstract Context provideContext(Application application);

}
