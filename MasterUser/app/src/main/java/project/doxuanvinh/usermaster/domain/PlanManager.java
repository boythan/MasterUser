package project.doxuanvinh.usermaster.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import project.doxuanvinh.usermaster.data.entity.test.GithubUser;
import project.doxuanvinh.usermaster.data.entity.test.User;
import project.doxuanvinh.usermaster.data.net.Api;
import rx.Observable;

/**
 * Created by Do Xuan Vinh on 26/04/2017.
 */

public class PlanManager {
    private Api api;
    private Context context;
    private Realm realm;

    @Inject
    public PlanManager(Api api, @Named("activityContext") Context context) {
        this.api = api;
        this.context = context;
        realm =  Realm.getInstance(context);
    }

    public Observable<ArrayList<GithubUser>> getGithubUser() {
        return api.getUsers();
    }

    public void addUser(String userName){
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setName(userName);
        realm.commitTransaction();
    }

    public List<User> getAllUser(){
        return realm.allObjects(User.class);
    }

    public void closeRealm(){
        realm.close();
    }

}
