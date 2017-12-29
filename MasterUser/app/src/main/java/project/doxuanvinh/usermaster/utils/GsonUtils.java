package project.doxuanvinh.usermaster.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public class GsonUtils {
    private static Gson gson;
    private static Gson originalGson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .create();
        }
        return gson;
    }

    public static Gson getOriginalGson() {
        if (originalGson == null) {
            originalGson = new Gson();
        }
        return originalGson;
    }
}
