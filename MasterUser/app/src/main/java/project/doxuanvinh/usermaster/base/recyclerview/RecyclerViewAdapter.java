package project.doxuanvinh.usermaster.base.recyclerview;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import project.doxuanvinh.usermaster.base.recyclerview.holder.BindingViewHolder;
import project.doxuanvinh.usermaster.base.recyclerview.holder.SimpleViewHolder;
import project.doxuanvinh.usermaster.base.recyclerview.listener.OnItemChildViewClickListener;
import project.doxuanvinh.usermaster.base.recyclerview.listener.OnItemClickListener;
import project.doxuanvinh.usermaster.utils.Constant;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Do Xuan Vinh on 27/04/2017.
 */

public abstract class RecyclerViewAdapter<ITEM_T> extends RecyclerView.Adapter<SimpleViewHolder> {

    protected final ArrayList<ITEM_T> items;
    private @LayoutRes int itemLayout;
    /**
     * Array of view resId and corresponding OnClickListener
     */
    private SparseArray<OnItemChildViewClickListener> mChildViewClickListeners;

    /**
     * Item click listener
     */
    private OnItemClickListener<ITEM_T> onItemClickListener;

    private SparseArray<Object> mBindingData = new SparseArray<>();

    public RecyclerViewAdapter(@LayoutRes int itemLayout, ArrayList<ITEM_T> items) {
        this.items = new ArrayList<ITEM_T>(items);
        this.itemLayout = itemLayout;
        mChildViewClickListeners = new SparseArray<>();

    }

    @Override
    public final void onBindViewHolder(SimpleViewHolder holder, int position) {
        if (holder instanceof BindingViewHolder) {
            ((BindingViewHolder<ITEM_T>) holder).bind(getItem(position), mBindingData);
        }
    }

    public ITEM_T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        SimpleViewHolder holder;
        holder = new BindingViewHolder<>(viewItem);

        ((BindingViewHolder<ITEM_T>) holder).setOnItemClickListener(onItemClickListener);

        for (int i = 0; i < mChildViewClickListeners.size(); i++) {
            int resId = mChildViewClickListeners.keyAt(i);
            OnItemChildViewClickListener listener = mChildViewClickListeners.valueAt(i);
            View childView = ((BindingViewHolder<ITEM_T>) holder).itemView.findViewById(resId);
            if (childView != null) {
                RxView.clicks(childView)
                        .throttleFirst(Constant.THROTTLE_FIRST_TIME, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                        .subscribe(aVoid -> {
                            listener.onItemChildViewClicked(childView, ((BindingViewHolder<ITEM_T>) holder).getData(), holder.getAdapterPosition());
                        });
            }
        }
        return holder;
    }

    public void setItems(ArrayList<ITEM_T> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setOnItemChildViewClickListener(@IdRes int resId, OnItemChildViewClickListener listener) {
        mChildViewClickListeners.put(resId, listener);
    }

    public void setOnItemClickListener(OnItemClickListener<ITEM_T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
