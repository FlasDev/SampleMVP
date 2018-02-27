package com.oleg.mvpprofile.ui;

import android.util.Log;

import com.oleg.mvpprofile.db.Profile;
import com.oleg.mvpprofile.db.ProfileDatabase;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by oleg on 26.02.2018.
 */

public class ProfileActivityModel implements ProfileActivityMVP.Model{
    private ProfileDatabase mProfileDatabase;

    @Inject
    public ProfileActivityModel(ProfileDatabase profileDatabase) {
        mProfileDatabase = profileDatabase;
    }

    @Override
    public void insertUser(Profile user) {
        Log.d("myLogs","Model-insertUser");
        Flowable.just(user)
                .subscribeOn(Schedulers.io())
                .doOnNext(aVoid->Log.d("myLogs",""+Thread.currentThread()))
                .doOnNext(user1->mProfileDatabase.profileDao().insert(user1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void updateUser(Profile user) {
        mProfileDatabase.profileDao().getMyPorfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public Flowable<Profile> getUser() {
        Log.d("myLogs","Model-getUser");
        return  mProfileDatabase.profileDao().getMyPorfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
