package project.doxuanvinh.usermaster.viewmodel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import project.doxuanvinh.usermaster.R;
import project.doxuanvinh.usermaster.base.recyclerview.RecyclerViewAdapter;
import project.doxuanvinh.usermaster.base.recyclerview.holder.SimpleViewHolder;
import project.doxuanvinh.usermaster.base.viewmodel.RecyclerViewModel;
import project.doxuanvinh.usermaster.data.entity.test.GithubUser;
import project.doxuanvinh.usermaster.data.entity.test.User;
import project.doxuanvinh.usermaster.domain.PlanManager;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Do Xuan Vinh on 26/04/2017.
 */

public class MainViewModel extends RecyclerViewModel {
    private PlanManager mManager;
    private Context mContext;
    @Inject
    public MainViewModel(PlanManager mManager, @Named("activityContext") Context mContext) {
        super();
        this.mManager = mManager;
        this.mContext = mContext;
    }


    @Override
    public RecyclerViewAdapter<User> getAdapter() {
        return new RecyclerViewAdapter<User>(R.layout.item_user_git, new ArrayList<>(mManager.getAllUser())) {
            @Override
            public void onBindViewHolder(SimpleViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
            }
        };
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    public void addUser(String userName) {
        mManager.addUser(userName);
        refresh(new ArrayList<>(mManager.getAllUser()));
    }

    public void destroyRealm() {
        mManager.closeRealm();
    }
}
