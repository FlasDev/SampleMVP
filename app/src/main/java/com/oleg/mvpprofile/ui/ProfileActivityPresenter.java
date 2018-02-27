package com.oleg.mvpprofile.ui;

import android.support.annotation.Nullable;
import android.util.Log;

import com.oleg.mvpprofile.db.Profile;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by oleg on 26.02.2018.
 */

public class ProfileActivityPresenter implements ProfileActivityMVP.Presenter {
    @Nullable
    private ProfileActivityMVP.View mView;
    private ProfileActivityMVP.Model mModel;
    @Inject
    public ProfileActivityPresenter(ProfileActivityMVP.Model model) {
        mModel = model;
    }

    @Override
    public void setView(ProfileActivityMVP.View view) {
        mView = view;
    }

    @Override
    public void getCurrentUser() {
        Log.d("myLogs","getCurrentUser");
        mModel.getUser()
                .filter(aVoid->mView!=null)
                .filter(user->user!=null)
                .doOnNext(user->Flowable.fromArray(user)
                        .doOnNext(profile -> mView.setUser(profile))
                        .subscribe()
                )
                .subscribe();
    }

    @Override
    public void insertCurrentUser() {
        Profile profile = new Profile();
        profile.name = mView.getName();
        profile.surname = mView.getSurname();
        profile.email = mView.getEmail();
        profile.mobile = mView.getMobile();
        profile.id = 1;
        profile.date = mView.getBirthDay();
        Flowable.just(profile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile1 -> mModel.insertUser(profile1));
    }
}
