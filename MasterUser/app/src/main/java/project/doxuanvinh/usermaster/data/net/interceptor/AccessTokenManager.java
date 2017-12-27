package project.doxuanvinh.usermaster.data.net.interceptor;

import android.text.TextUtils;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public class AccessTokenManager {
    private String token;
    private AccessTokenCache accessTokenCache;

    private static AccessTokenManager instance;

    public static AccessTokenManager getInstance() {
        if (instance == null) {
            instance = new AccessTokenManager();
        }
        return instance;
    }

    private AccessTokenManager() {
        this(new AccessTokenCache());
    }

    private AccessTokenManager(AccessTokenCache accessTokenCache) {
        this.accessTokenCache = accessTokenCache;
        setCurrentAccessToken(accessTokenCache.getAccessToken(), false);
    }

    public void setCurrentAccessToken(String accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    private void setCurrentAccessToken(String accessToken, boolean saveToCache) {
        this.token = accessToken;
        if (saveToCache) {
            accessTokenCache.save(accessToken);
        }
    }

    public String getToken() {
        return token;
    }

    public boolean hasToken() {
        return !TextUtils.isEmpty(token);
    }

    public void clear() {
        accessTokenCache.clear();
        token = null;
    }

    public static void destroy(){
        instance = null;
    }
}
