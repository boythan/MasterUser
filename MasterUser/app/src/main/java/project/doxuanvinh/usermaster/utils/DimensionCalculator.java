package project.doxuanvinh.usermaster.utils;

import android.content.Context;
import android.util.TypedValue;

import project.doxuanvinh.usermaster.R;

/**
 * Created by Do Xuan Vinh on 29/12/2017.
 */

public class DimensionCalculator extends DC {

    private static DimensionCalculator instance;
    private int mActionBarHeight;

    protected DimensionCalculator(Context context) {
        super(context);
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            mActionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
    }

    public int getActionBarHeight() {
        return mActionBarHeight;
    }

    public static DimensionCalculator getInstance(Context context) {
        if (instance == null) {
            instance = new DimensionCalculator(context);
        }
        return instance;
    }


}
