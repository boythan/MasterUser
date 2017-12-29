package project.doxuanvinh.usermaster.data.net.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import project.doxuanvinh.usermaster.App;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public class AccessTokenCache {
    static final String CACHED_ACCESS_TOKEN_KEY
            = "io.fruitful.AccessTokenManager.CachedAccessToken";

    static final String SHARED_PREFERENCES_NAME =
            "io.fruitful.AccessTokenManager.SharedPreferences";
    private final SharedPreferences sharedPreferences;

    public AccessTokenCache() {
        this(App.getInstance().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE));
    }

    public AccessTokenCache(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void save(String accessToken) {
        sharedPreferences.edit().putString(CACHED_ACCESS_TOKEN_KEY, accessToken).apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(CACHED_ACCESS_TOKEN_KEY, null);
    }

    public void clear() {
        sharedPreferences.edit().remove(CACHED_ACCESS_TOKEN_KEY).apply();
    }
}
