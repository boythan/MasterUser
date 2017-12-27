package project.doxuanvinh.usermaster.domain;

import java.util.ArrayList;

import javax.inject.Inject;

import project.doxuanvinh.usermaster.data.entity.test.GithubUser;
import project.doxuanvinh.usermaster.data.net.Api;
import rx.Observable;

/**
 * Created by Do Xuan Vinh on 26/04/2017.
 */

public class PlanManager {
    private Api api;


    @Inject
    public PlanManager(Api api) {
        this.api = api;
    }

    public Observable<ArrayList<GithubUser>> getGithubUser(){
        return api.getUsers();
    }

}
