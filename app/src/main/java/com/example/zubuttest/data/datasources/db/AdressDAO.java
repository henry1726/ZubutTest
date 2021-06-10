package com.example.zubuttest.data.datasources.db;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.zubuttest.data.entities.AdressEntity;

import java.util.List;

@Dao
public interface AdressDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAdress(AdressEntity adressEntity);

    @Query("SELECT * FROM direcciones")
    LiveData<List<AdressEntity>> readAllAdress();

    @Query("SELECT coordenadas FROM direcciones")
    LiveData<List<String>> readAllCoordinates();

    @Query("DELETE FROM direcciones")
    void deleteAdress();
}
