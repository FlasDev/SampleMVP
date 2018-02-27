package com.oleg.mvpprofile.ui;

import com.oleg.mvpprofile.db.Profile;

import io.reactivex.Flowable;

/**
 * Created by oleg on 26.02.2018.
 */

public interface ProfileActivityMVP {
    interface View{
        String getName();
        String getSurname();
        String getEmail();
        String getMobile();
        String getBirthDay();
        void setUser(Profile user);

    }

    interface Presenter{
        void setView(ProfileActivityMVP.View view);
        void getCurrentUser();
        void insertCurrentUser();
    }

    interface Model{
        void insertUser(Profile user);
        void updateUser(Profile user);
        Flowable<Profile> getUser();
    }
}
