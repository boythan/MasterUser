package project.doxuanvinh.usermaster.di.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import project.doxuanvinh.usermaster.App;
import project.doxuanvinh.usermaster.data.net.Api;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */
@Module
public class ApplicationModule {
    private App application;
    private Api api;

    public ApplicationModule(App application, Api api) {
        this.application = application;
        this.api = api;
    }

    @Singleton
    @Provides
    public Api provideApi() {
        return api;
    }

    @Provides
    @Singleton
    @Named("applicationContext")
    public Context provideContext() {
        return application;
    }
}
