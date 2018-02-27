package com.oleg.mvpprofile.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by oleg on 26.02.2018.
 */

@Database(entities = {Profile.class},version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
    public abstract ProfileDao profileDao();
}
