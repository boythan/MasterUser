package project.doxuanvinh.usermaster;

import android.content.Context;
import android.support.multidex.MultiDexApplication;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import project.doxuanvinh.usermaster.data.net.Api;
import project.doxuanvinh.usermaster.data.net.interceptor.UnauthorizedInterceptor;
import project.doxuanvinh.usermaster.di.components.ApplicationComponent;
import project.doxuanvinh.usermaster.di.components.DaggerApplicationComponent;
import project.doxuanvinh.usermaster.di.modules.ApplicationModule;
import project.doxuanvinh.usermaster.utils.GsonUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public class App extends MultiDexApplication {
    private ApplicationComponent applicationComponent;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeApp();
    }

    private void initializeApp() {
        OkHttpClient client = createOkHttpClient(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        Api api = retrofit.create(Api.class);
        initInjector(api);
        initCalligraphy();
    }

    private void initInjector(Api api) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, api))
                .build();
    }

    private OkHttpClient createOkHttpClient(Context context) {
        final File cacheDir = context.getCacheDir();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        UnauthorizedInterceptor unauthorizedInterceptor = new UnauthorizedInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(new Cache(cacheDir, 10 * 1024 * 1024))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(unauthorizedInterceptor );
//                .addInterceptor(new ChuckInterceptor(context));

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.custom_font))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
