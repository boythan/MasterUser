package project.doxuanvinh.usermaster.di.components;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import project.doxuanvinh.usermaster.data.net.Api;
import project.doxuanvinh.usermaster.di.modules.ApplicationModule;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Api api();

    @Named("applicationContext")
    Context context();
}
