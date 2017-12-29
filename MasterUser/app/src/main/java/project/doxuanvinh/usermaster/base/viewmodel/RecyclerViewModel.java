package project.doxuanvinh.usermaster.base.viewmodel;

import android.databinding.Bindable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import project.doxuanvinh.usermaster.base.recyclerview.RecyclerViewAdapter;

/**
 * Created by Do Xuan Vinh on 22/05/2017.
 */

public abstract class RecyclerViewModel<ITEM> extends ViewModel {
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter<ITEM> adapter;

    protected abstract RecyclerViewAdapter<ITEM> createAdapter();

    protected abstract RecyclerView.LayoutManager createLayoutManager();

    public RecyclerViewModel() {
        super();

    }

    public final void setupRecyclerView(RecyclerView recyclerView) {
        layoutManager = createLayoutManager();
        adapter = createAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public final void onRefreshRecyclerView(SwipeRefreshLayout refreshLayout) {
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(true);
            (new Handler()).postDelayed(() -> {
                refreshLayout.setRefreshing(false);

            }, 3000);
        });

    }

    public void refresh(ArrayList<ITEM> items) {
        adapter.setItems(items);
    }

    @Bindable
    public RecyclerViewAdapter<ITEM> getAdapter() {
        return adapter;
    }
}
