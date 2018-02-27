package com.oleg.mvpprofile.di.module;

import com.oleg.mvpprofile.db.ProfileDatabase;
import com.oleg.mvpprofile.ui.ProfileActivityMVP;
import com.oleg.mvpprofile.ui.ProfileActivityModel;
import com.oleg.mvpprofile.ui.ProfileActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oleg on 26.02.2018.
 */
@Module
public class ProfileModule {

    @Provides
    public ProfileActivityMVP.Presenter provideProfilePresenter(ProfileActivityMVP.Model model){
        return new ProfileActivityPresenter(model);
    }

    @Provides
    public ProfileActivityMVP.Model provideProfileActivityModel(ProfileDatabase profileDatabase){
        return new ProfileActivityModel(profileDatabase);
    }
}
