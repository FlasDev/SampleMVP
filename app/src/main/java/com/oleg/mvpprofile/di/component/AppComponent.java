package com.oleg.mvpprofile.di.component;

import android.content.Context;

import com.oleg.mvpprofile.App;
import com.oleg.mvpprofile.di.module.ActivityBuilder;
import com.oleg.mvpprofile.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by oleg on 26.02.2018.
 */
@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class, ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder{
        AppComponent build();
        @BindsInstance
        Builder application(Context context);
    }

    void inject(App app);
}
