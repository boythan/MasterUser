package project.doxuanvinh.usermaster.base.uitls;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Do Xuan Vinh on 24/03/2017.
 */

public class RecyclerViewItemCountAssertion implements ViewAssertion {
    private final Matcher<Integer> matcher;
    private int randomItemPosition;
    private int totalItem;
    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.matcher = is(expectedCount);
    }

    public RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }


    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        setTotalItem(adapter.getItemCount());
        assertThat(adapter.getItemCount(), matcher);
    }

    public Matcher<Integer> getMatcher() {
        return matcher;
    }

    public int getRandomItemPosition() {
        Random ran = new Random();
        if(totalItem == 0) return totalItem;
        randomItemPosition =  ran.nextInt(totalItem);
        return randomItemPosition;
    }

    public void setRandomItemPosition(int randomItemPosition) {
        this.randomItemPosition = randomItemPosition;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }
}
