package project.doxuanvinh.usermaster.data.net;

import java.util.ArrayList;

import project.doxuanvinh.usermaster.data.entity.test.GithubUser;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public interface Api {
    @GET("https://api.github.com/users")
    Observable<ArrayList<GithubUser>> getUsers();
}
