package project.doxuanvinh.usermaster.base.recyclerview.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.view.View;

import project.doxuanvinh.usermaster.BR;

/**
 * Created by Do Xuan Vinh on 19/08/2017.
 */

public class BindingViewHolder<Item> extends SimpleViewHolder {
    private Item item;
    private ViewDataBinding binding;
    public BindingViewHolder(View itemView) {
        super(itemView);
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
}
