package project.doxuanvinh.usermaster.di.components;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Component;
import project.doxuanvinh.usermaster.di.PerActivity;
import project.doxuanvinh.usermaster.di.modules.ActivityModule;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    @Named("activityContext")
    Context activityContext();
    Activity activity();
}
