package com.example.zubuttest.sys.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.example.zubuttest.data.datasources.db.declaration.AppDataBase;
import com.example.zubuttest.sys.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Singleton
    @Provides
    AppDataBase provideAppDataBase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, Constants.Db.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
