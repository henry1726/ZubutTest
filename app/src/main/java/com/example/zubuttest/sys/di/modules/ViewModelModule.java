package com.example.zubuttest.sys.di.modules;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.zubuttest.ui.adress.AdressFragmentViewModel;
import com.example.zubuttest.ui.principal.MapsActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class ViewModelModule {

    @Provides
    AdressFragmentViewModel provideAdressFragmentViewModel(Fragment fragment) {
        return ViewModelProviders.of(fragment).get(AdressFragmentViewModel.class);
    }

}
