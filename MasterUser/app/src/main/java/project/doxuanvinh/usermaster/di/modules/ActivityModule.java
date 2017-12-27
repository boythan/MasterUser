package project.doxuanvinh.usermaster.di.modules;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import project.doxuanvinh.usermaster.base.ui.BaseActivity;
import project.doxuanvinh.usermaster.di.PerActivity;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */
@Module
public class ActivityModule {
    BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @Named("activityContext")
    Context provideContext() {
        return activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }
}
