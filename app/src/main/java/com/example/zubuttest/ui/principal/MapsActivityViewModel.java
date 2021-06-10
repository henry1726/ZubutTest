package com.example.zubuttest.ui.principal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.zubuttest.MainApplication;
import com.example.zubuttest.data.datasources.db.AdressDAO;
import com.example.zubuttest.domain.AdressRepository;
import com.example.zubuttest.domain.MapsAcitivityRepository;
import com.example.zubuttest.sys.di.components.DaggerDataSourceComponent;
import com.example.zubuttest.sys.di.components.DaggerRepositoryComponent;
import com.example.zubuttest.sys.di.modules.ContextModule;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MapsActivityViewModel extends ViewModel {

    @Inject
    MapsAcitivityRepository mapsAcitivityRepository;

    public MapsActivityViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }

    public LiveData<List<LatLng>> readAllCordinates(){
        List<LatLng> list = new ArrayList<>();

        return Transformations.switchMap(mapsAcitivityRepository.readAllCoordinates(), coordinate ->{
            for(String coordenada : coordinate){
                String value = coordenada.substring(1, coordenada.length() - 1)
                        .replace(" ", "");

                String[] values = value.split(",");
                LatLng latLng = new LatLng(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
                list.add(latLng);

            }
            return new MutableLiveData<>(list);
        });
    }

    public void insertAdress(String nombre, String direccion, String coordenadas){
        mapsAcitivityRepository.insertAdress(nombre, direccion, coordenadas);
    }
}
