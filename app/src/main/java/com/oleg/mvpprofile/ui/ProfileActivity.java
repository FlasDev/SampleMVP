package com.oleg.mvpprofile.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ImageButton;

import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxMenuItem;
import com.jakewharton.rxbinding2.view.RxView;
import com.oleg.mvpprofile.R;
import com.oleg.mvpprofile.databinding.AppBarBinding;
import com.oleg.mvpprofile.db.Profile;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.trello.rxlifecycle2.android.ActivityEvent.DESTROY;

public class ProfileActivity extends RxAppCompatActivity implements ProfileActivityMVP.View{
    AppBarBinding mBinding;
    @Inject
    ProfileActivityMVP.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar);
        AndroidInjection.inject(this);
        mBinding = DataBindingUtil.setContentView(this,R.layout.app_bar);
        initUI();
    }

    private void initUI() {
        setToolbar(mBinding.toolbar);
        setBirtthdayButton(mBinding.activityMain.buttonBirthday);
        setAddPhotoButton(mBinding.activityMain.addPhoto);
    }

    private void setAddPhotoButton(ImageButton button) {
        RxView.clicks(button)
                .compose(bindUntilEvent(DESTROY))
                .subscribe();
    }

    @Override
    public String getName() {
        return mBinding.activityMain.name.getText().toString();
    }

    @Override
    public String getSurname() {
        return mBinding.activityMain.surname.getText().toString();
    }

    @Override
    public String getEmail() {
        return mBinding.activityMain.email.getText().toString();
    }

    @Override
    public String getMobile() {
        return mBinding.activityMain.phoneNumber.getText().toString();
    }

    @Override
    public String getBirthDay() {
        return mBinding.activityMain.buttonBirthday.getText().toString();
    }

    @Override
    public void setUser(Profile user) {
        mBinding.activityMain.name.setText(user.name);
        mBinding.activityMain.surname.setText(user.surname);
        mBinding.activityMain.email.setText(user.email);
        mBinding.activityMain.phoneNumber.setText(user.mobile);
        mBinding.activityMain.buttonBirthday.setText(user.date);
    }


    private void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("MVP");
        setSupportActionBar(toolbar);
        RxToolbar.navigationClicks(toolbar)
                .compose(bindUntilEvent(DESTROY))
                .subscribe();

    }

    private void setBirtthdayButton(Button birtthdayButton) {
        RxView.clicks(birtthdayButton)
                .compose(bindUntilEvent(DESTROY))
                .subscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
        mPresenter.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        RxMenuItem.clicks(menu.findItem(R.id.done))
                .compose(bindUntilEvent(DESTROY))
                .subscribe(aVoid->mPresenter.insertCurrentUser());
        return true;
    }
}
