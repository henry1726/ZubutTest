package com.example.zubuttest.sys.di.components;

import com.example.zubuttest.sys.di.modules.ViewModelModule;
import com.example.zubuttest.ui.adress.AdressFragment;
import com.example.zubuttest.ui.principal.MapsActivity;

import dagger.Component;

@Component(modules = ViewModelModule.class)
public interface ViewModelComponent {

    void inject(AdressFragment view);
}
