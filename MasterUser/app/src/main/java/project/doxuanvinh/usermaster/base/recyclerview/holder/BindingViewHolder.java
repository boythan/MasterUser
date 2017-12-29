package project.doxuanvinh.usermaster.base.recyclerview.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import project.doxuanvinh.usermaster.BR;
import project.doxuanvinh.usermaster.base.recyclerview.listener.OnItemClickListener;
import project.doxuanvinh.usermaster.utils.Constant;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Do Xuan Vinh on 19/08/2017.
 */

public class BindingViewHolder<Item> extends SimpleViewHolder {
    private Item item;
    private ViewDataBinding binding;
    private OnItemClickListener<Item> onItemClickListener;

    public BindingViewHolder(View itemView) {
        super(itemView);
        RxView.clicks(itemView).throttleFirst(Constant.THROTTLE_FIRST_TIME, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(aVoid -> onClick());

        binding = DataBindingUtil.bind(itemView);
    }

    public Item getData() {
        return item;
    }

    public void bind(Item item, SparseArray bindingMap) {
        this.item = item;
        if (binding != null) {
            binding.setVariable(BR.item, item);
//            binding.setVariable(BR.itemPosition, position);
            int bindingSize = bindingMap.size();
            for (int i = 0; i < bindingSize; i++) {
                binding.setVariable(bindingMap.keyAt(i), bindingMap.valueAt(i));
            }

            binding.executePendingBindings();
        }
    }

    private void onClick() {
        int position = getAdapterPosition();

        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(itemView, getData(), position);
        }
    }
    public void setOnItemClickListener(OnItemClickListener<Item> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
