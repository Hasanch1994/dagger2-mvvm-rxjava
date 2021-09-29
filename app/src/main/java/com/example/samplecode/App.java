package com.example.samplecode;
import com.example.samplecode.di.components.AppComponent;
import com.example.samplecode.di.components.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class App extends dagger.android.support.DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}
