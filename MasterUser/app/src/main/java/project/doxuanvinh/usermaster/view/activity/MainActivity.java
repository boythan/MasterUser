package project.doxuanvinh.usermaster.view.activity;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
import project.doxuanvinh.usermaster.utils.AppUtils;
import project.doxuanvinh.usermaster.utils.Constant;
import project.doxuanvinh.usermaster.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private View rootView;

    @Inject
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        rootView = binding.getRoot();

        binding.setViewModel(mViewModel);

        RxView.clicks(binding.imgAdd).throttleFirst(Constant.THROTTLE_FIRST_TIME, TimeUnit.MILLISECONDS).subscribe(o -> {
            onClickAddUser();
        });

    }

    private void onClickAddUser() {
        final Dialog mDialog = new Dialog(this, R.style.Dialog_Transparent);
        AppUtils.setOverScreenDialog(mDialog, this);

        mDialog.setContentView(R.layout.layout_add_user);
        TextView tvOk = (TextView) mDialog.findViewById(R.id.tv_ok);
        TextView tvCancel = (TextView) mDialog.findViewById(R.id.tv_cancel);
        EditText edtUserName = (EditText) mDialog.findViewById(R.id.edt_add_user);
        RxView.clicks(tvCancel).throttleFirst(Constant.THROTTLE_FIRST_TIME, TimeUnit.SECONDS).subscribe(aVoid1 -> {
            mDialog.dismiss();
        });
        RxView.clicks(tvOk).throttleFirst(Constant.THROTTLE_FIRST_TIME, TimeUnit.SECONDS).subscribe(aVoid1 -> {
            mViewModel.addUser(edtUserName.getText().toString());
            mDialog.dismiss();
        });
        mDialog.show();

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
        mViewModel.destroyRealm();
        super.onDestroy();
    }
}
