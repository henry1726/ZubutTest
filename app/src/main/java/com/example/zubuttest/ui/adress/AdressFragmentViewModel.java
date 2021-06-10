package com.example.zubuttest.ui.adress;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zubuttest.MainApplication;
import com.example.zubuttest.data.entities.AdressEntity;
import com.example.zubuttest.domain.AdressRepository;
import com.example.zubuttest.sys.di.components.DaggerRepositoryComponent;
import com.example.zubuttest.sys.di.modules.ContextModule;

import java.util.List;

import javax.inject.Inject;

public class AdressFragmentViewModel extends ViewModel {

    @Inject
    AdressRepository adressRepository;

    public AdressFragmentViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }

    public LiveData<List<AdressEntity>> getAllAdress(){
        return adressRepository.getAllAdress();
    }
}
