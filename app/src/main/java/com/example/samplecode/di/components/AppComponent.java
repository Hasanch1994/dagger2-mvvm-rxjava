package com.example.samplecode.di.components;

import android.app.Application;

import com.example.samplecode.App;
import com.example.samplecode.di.modules.ActivityBindingModule;
import com.example.samplecode.di.modules.NetworkModule;
import com.example.samplecode.di.modules.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ContextModule.class, AndroidSupportInjectionModule.class, NetworkModule.class, ActivityBindingModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(App instance);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}

