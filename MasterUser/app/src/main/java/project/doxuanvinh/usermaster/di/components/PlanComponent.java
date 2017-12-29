package project.doxuanvinh.usermaster.di.components;

import dagger.Component;
import project.doxuanvinh.usermaster.di.PerActivity;
import project.doxuanvinh.usermaster.di.modules.ActivityModule;
import project.doxuanvinh.usermaster.di.modules.PlanModule;
import project.doxuanvinh.usermaster.view.activity.MainActivity;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */
@PerActivity
@Component(modules = {PlanModule.class, ActivityModule.class}, dependencies = ApplicationComponent.class)
public interface PlanComponent {
    void inject(MainActivity activity);
}
