package project.doxuanvinh.usermaster.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by Do Xuan Vinh on 29/12/2017.
 */

public class DC {

    protected int width;
    protected int height;
    protected int screenWidth;
    protected int screenHeight;

    protected DC(Context context) {
        Resources res = context.getResources();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        int statusBarHeight;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        } else {
            // if found nothing get status bar height = 25 dp
            statusBarHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, dm);
        }
        height = dm.heightPixels - statusBarHeight;

        if (OSUtils.hasJellyBeanMR1()) {
            wm.getDefaultDisplay().getRealMetrics(dm);
        }

        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public static int[] getImageSize(Resources res, @DrawableRes int resId) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static int dp2px(Context context, float dp){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}