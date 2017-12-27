package project.doxuanvinh.usermaster.base.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import project.doxuanvinh.usermaster.base.recyclerview.holder.BindingViewHolder;
import project.doxuanvinh.usermaster.base.recyclerview.holder.SimpleViewHolder;


/**
 * Created by Do Xuan Vinh on 27/04/2017.
 */

public abstract class RecyclerViewAdapter<ITEM_T> extends RecyclerView.Adapter<SimpleViewHolder> {

    protected final ArrayList<ITEM_T> items;
    private @LayoutRes int itemLayout;

    private SparseArray<Object> mBindingData = new SparseArray<>();

    public RecyclerViewAdapter(@LayoutRes int itemLayout, ArrayList<ITEM_T> items) {
        this.items = new ArrayList<ITEM_T>(items);
        this.itemLayout = itemLayout;
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
        return holder;
    }

    public void setItems(ArrayList<ITEM_T> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

}
