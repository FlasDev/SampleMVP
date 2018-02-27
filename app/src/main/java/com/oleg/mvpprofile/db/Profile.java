package com.oleg.mvpprofile.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by oleg on 26.02.2018.
 */
@Entity
public class Profile {

    @PrimaryKey
    public long id;

    public String name;

    public String surname;

    public String mobile;

    public String email;

    public String date;

}
