package project.doxuanvinh.usermaster.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.realm.Realm;
import project.doxuanvinh.usermaster.App;
import project.doxuanvinh.usermaster.R;
import project.doxuanvinh.usermaster.base.ui.BaseActivity;
import project.doxuanvinh.usermaster.data.entity.test.GithubUser;
import project.doxuanvinh.usermaster.data.entity.test.User;
import project.doxuanvinh.usermaster.databinding.ActivityMainBinding;
import project.doxuanvinh.usermaster.di.components.DaggerPlanComponent;
import project.doxuanvinh.usermaster.di.modules.PlanModule;
import project.doxuanvinh.usermaster.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private View rootView;
    private Realm mRealm;

    @Inject
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        rootView = binding.getRoot();

        mRealm = Realm.getInstance(this);
        mViewModel.setListUser(new ArrayList<>(mRealm.allObjects(User.class)));

        binding.setViewModel(mViewModel);

        RxView.clicks(binding.btnAdd).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(o -> {
            mRealm.beginTransaction();
            User user = mRealm.createObject(User.class);
            user.setName("Vinh");
            mRealm.commitTransaction();
            mViewModel.refresh(new ArrayList<>(mRealm.allObjects(User.class)));
        });

    }


    public void inject() {
        DaggerPlanComponent.builder().applicationComponent(App.getInstance()
                .getApplicationComponent())
                .planModule(new PlanModule())
                .activityModule(getActivityModule())
                .build().inject(this);
    }

    @Override
    public void startActivity() {
        inject();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
