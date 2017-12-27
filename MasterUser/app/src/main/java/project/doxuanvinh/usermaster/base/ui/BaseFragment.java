package project.doxuanvinh.usermaster.base.ui;

import android.app.Fragment;

import rx.subjects.BehaviorSubject;

/**
 * Created by Do Xuan Vinh on 25/04/2017.
 */

public class BaseFragment extends Fragment{
    private final BehaviorSubject<BaseFragment> preDestroy = BehaviorSubject.create();

    protected BehaviorSubject<BaseFragment> preDestroy() {
        return preDestroy;
    }

    @Override
    public void onDestroyView() {
        preDestroy.onNext(this);
        super.onDestroyView();
    }
}
