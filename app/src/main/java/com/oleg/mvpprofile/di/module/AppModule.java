package com.oleg.mvpprofile.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.oleg.mvpprofile.db.ProfileDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oleg on 26.02.2018.
 */
@Module
public  class AppModule {

    @Singleton
    @Provides
    ProfileDatabase provideDatabase(Context context){
        return Room.databaseBuilder(context,ProfileDatabase.class,"profiledatabase").build();
    }
}
