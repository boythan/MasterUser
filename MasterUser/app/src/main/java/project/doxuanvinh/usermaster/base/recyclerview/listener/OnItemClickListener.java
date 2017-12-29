package project.doxuanvinh.usermaster.base.recyclerview.listener;

import android.view.View;

/**
 * Created by Do Xuan Vinh on 29/12/2017.
 */

public interface OnItemClickListener<T> {
    void onItemClick(View itemView, T data, int position);
}
