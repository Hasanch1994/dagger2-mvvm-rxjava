package com.example.samplecode.di.modules;

import androidx.lifecycle.ViewModel;

import com.example.samplecode.di.keys.ViewModelKey;
import com.example.samplecode.ui.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModules {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainviewModel(MainViewModel mainViewModel);
}
