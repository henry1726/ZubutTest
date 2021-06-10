package com.example.zubuttest.sys.di.components;

import com.example.zubuttest.sys.di.modules.RepositoryModule;
import com.example.zubuttest.ui.adress.AdressFragmentViewModel;
import com.example.zubuttest.ui.principal.MapsActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {

    void inject(MapsActivityViewModel viewModel);

    void inject(AdressFragmentViewModel viewModel);

}
