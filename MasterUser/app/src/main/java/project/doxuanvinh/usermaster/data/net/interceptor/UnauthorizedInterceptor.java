package project.doxuanvinh.usermaster.data.net.interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public class UnauthorizedInterceptor implements Interceptor{
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();

        String accessToken = AccessTokenManager.getInstance().getToken();
        if (TextUtils.isEmpty(accessToken)) {
            return chain.proceed(originalRequest);
        }

        Request tokenAddedRequest = originalRequest.newBuilder()
                .addHeader("X-Auth-Token", accessToken)
                .method(originalRequest.method(), originalRequest.body())
                .build();
        return chain.proceed(tokenAddedRequest);
    }
}
