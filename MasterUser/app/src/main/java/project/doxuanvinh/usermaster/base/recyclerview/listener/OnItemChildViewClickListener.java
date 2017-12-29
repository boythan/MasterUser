package project.doxuanvinh.usermaster.base.recyclerview.listener;

import android.view.View;

/**
 * Created by Do Xuan Vinh on 29/12/2017.
 */

public interface OnItemChildViewClickListener<T> {
    void onItemChildViewClicked(View view, T data, int position);

}
