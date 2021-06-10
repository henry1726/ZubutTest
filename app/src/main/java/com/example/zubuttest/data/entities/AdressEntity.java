package com.example.zubuttest.data.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "direcciones")
public class AdressEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nombre")
    private String name;

    @ColumnInfo(name = "direccion")
    private String adress;

    @ColumnInfo(name = "coordenadas")
    private String coordinate;

    public AdressEntity(String name, String adress, String coordinate) {
        this.name = name;
        this.adress = adress;
        this.coordinate = coordinate;
    }

    @Ignore


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
