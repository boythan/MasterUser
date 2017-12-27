package project.doxuanvinh.usermaster.data.entity.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Do Xuan Vinh on 26/04/2017.
 */

public class GithubUser {
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public GithubUser(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

//    "login": "mojombo",
//            "id": 1,
//            "avatar_url": "https://avatars.githubusercontent.com/u/1?v=3",
//            "gravatar_id": "",
//            "url": "https://api.github.com/users/mojombo",
//            "html_url": "https://github.com/mojombo",
//            "followers_url": "https://api.github.com/users/mojombo/followers",
//            "following_url": "https://api.github.com/users/mojombo/following{/other_user}",
//            "gists_url": "https://api.github.com/users/mojombo/gists{/gist_id}",
//            "starred_url": "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
//            "subscriptions_url": "https://api.github.com/users/mojombo/subscriptions",
//            "organizations_url": "https://api.github.com/users/mojombo/orgs",
//            "repos_url": "https://api.github.com/users/mojombo/repos",
//            "events_url": "https://api.github.com/users/mojombo/events{/privacy}",
//            "received_events_url": "https://api.github.com/users/mojombo/received_events",
//            "type": "User",
//            "site_admin": false
}
