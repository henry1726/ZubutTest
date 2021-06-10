package com.example.zubuttest.data.datasources.db.declaration;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.zubuttest.data.datasources.db.AdressDAO;
import com.example.zubuttest.data.entities.AdressEntity;

@Database(entities = {
        AdressEntity.class
}, version = 1, exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {

    public abstract AdressDAO getAdressDAO();

}
