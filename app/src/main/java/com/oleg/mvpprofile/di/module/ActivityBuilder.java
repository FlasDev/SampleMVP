package com.oleg.mvpprofile.di.module;

import com.oleg.mvpprofile.ui.ProfileActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by oleg on 26.02.2018.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {ProfileModule.class})
    abstract ProfileActivity bindProfileActivity();
}
