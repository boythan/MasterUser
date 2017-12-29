package project.doxuanvinh.usermaster.viewmodel;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import project.doxuanvinh.usermaster.R;
import project.doxuanvinh.usermaster.base.recyclerview.RecyclerViewAdapter;
import project.doxuanvinh.usermaster.base.recyclerview.holder.SimpleViewHolder;
import project.doxuanvinh.usermaster.base.viewmodel.RecyclerViewModel;
import project.doxuanvinh.usermaster.data.entity.test.User;
import project.doxuanvinh.usermaster.domain.PlanManager;
import project.doxuanvinh.usermaster.utils.AppUtils;
import project.doxuanvinh.usermaster.utils.Constant;

/**
 * Created by Do Xuan Vinh on 26/04/2017.
 */

public class MainViewModel extends RecyclerViewModel {
    private PlanManager mManager;
    private Context mContext;
    private RecyclerViewAdapter userAdapter;

    @Inject
    public MainViewModel(PlanManager mManager, @Named("activityContext") Context mContext) {
        super();
        this.mManager = mManager;
        this.mContext = mContext;
    }


    @Override
    public RecyclerViewAdapter<User> createAdapter() {
        userAdapter = new RecyclerViewAdapter<User>(R.layout.item_user, new ArrayList<>(mManager.getAllUser())){};
        userAdapter.setOnItemClickListener((itemView, data, position) -> {
            Toast.makeText(mContext, position + " click item", Toast.LENGTH_LONG).show();
        });
//        userAdapter.setOnItemChildViewClickListener(R.id.layout_more, (view, data, position) -> {
//            onClickOptionItem((User) data);
//        });

        return userAdapter;
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    public void addUser(String userName) {
        mManager.addUser(userName);
        refresh(new ArrayList<>(mManager.getAllUser()));
    }

    public void deleteUser(User user) {
        mManager.deleteUser(user);
    }

    public void destroyRealm() {
        mManager.closeRealm();
    }

    private void onClickOptionItem(User user) {
        final Dialog mDialog = new Dialog(mContext, R.style.Dialog_Transparent);
        AppUtils.setOverScreenDialog(mDialog, mContext);

        mDialog.setContentView(R.layout.layout_option_user);
        TextView tvEdit = (TextView) mDialog.findViewById(R.id.tv_edit);
        TextView tvDelete = (TextView) mDialog.findViewById(R.id.tv_delete);

        RxView.clicks(tvEdit).throttleFirst(Constant.THROTTLE_FIRST_TIME, TimeUnit.SECONDS).subscribe(aVoid1 -> {
            mDialog.dismiss();
        });
        RxView.clicks(tvDelete).throttleFirst(Constant.THROTTLE_FIRST_TIME, TimeUnit.SECONDS).subscribe(aVoid1 -> {
            deleteUser(user);
            mDialog.dismiss();
        });
        mDialog.show();
    }
}
