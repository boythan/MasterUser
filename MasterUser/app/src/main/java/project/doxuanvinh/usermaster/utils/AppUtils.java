package project.doxuanvinh.usermaster.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Do Xuan Vinh on 29/12/2017.
 */

public class AppUtils {
    public static void setOverScreenDialog(Dialog mDialog, Context mContext) {
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.copyFrom(mDialog.getWindow().getAttributes());
        mLayoutParams.width = DimensionCalculator.getInstance(mContext).getWidth();
        mLayoutParams.height = DimensionCalculator.getInstance(mContext).getHeight();
        mDialog.getWindow().setAttributes(mLayoutParams);
    }
}
