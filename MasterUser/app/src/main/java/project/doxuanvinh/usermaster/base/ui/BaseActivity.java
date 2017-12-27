package project.doxuanvinh.usermaster.base.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import project.doxuanvinh.usermaster.di.modules.ActivityModule;
import rx.subjects.BehaviorSubject;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private final BehaviorSubject<BaseActivity> preDestroy = BehaviorSubject.create();

    protected BehaviorSubject<BaseActivity> preDestroy() {
        return preDestroy;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity();
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        preDestroy.onNext(this);
        super.onDestroy();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

     public abstract void startActivity();
}
