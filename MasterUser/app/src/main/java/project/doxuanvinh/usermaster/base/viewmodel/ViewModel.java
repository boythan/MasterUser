package project.doxuanvinh.usermaster.base.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.CallSuper;

/**
 * Created by Do Xuan Vinh on 22/05/2017.
 */

public abstract class ViewModel extends BaseObservable{
    protected ViewModel() {

    }

    @CallSuper
    public void onStart() {

    }

//    public State getInstanceState() {
//        return new State(this);
//    }

    @CallSuper
    public void onStop() {

    }

//    public static class State implements Parcelable {
//
//        protected State(ViewModel viewModel) {
//
//        }
//
//        public State(Parcel in) {
//
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @CallSuper
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//
//        }
//
//        public static Creator<State> CREATOR = new Creator<State>() {
//            @Override
//            public State createFromParcel(Parcel source) {
//                return new State(source);
//            }
//
//            @Override
//            public State[] newArray(int size) {
//                return new State[size];
//            }
//        };
//    }
}
