package com.oleg.mvpprofile.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by oleg on 26.02.2018.
 */

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM profile Where id = 1")
    Flowable<Profile> getMyPorfile();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Profile profile);

    @Delete
    void delete(Profile profile);
}