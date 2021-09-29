package com.example.samplecode.di.modules;

import com.example.samplecode.ui.main.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainAcitity();

}
