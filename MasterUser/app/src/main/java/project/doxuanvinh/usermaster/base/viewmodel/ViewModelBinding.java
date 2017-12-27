package project.doxuanvinh.usermaster.base.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Do Xuan Vinh on 22/05/2017.
 */

public class ViewModelBinding {
    @BindingAdapter("recyclerViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                RecyclerViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);
    }

    @BindingAdapter("onRefresh")
    public static void setOnRefreshRecycler(SwipeRefreshLayout swipeRefreshLayout, RecyclerViewModel viewModel) {
        viewModel.onRefreshRecyclerView(swipeRefreshLayout);
    }
}
