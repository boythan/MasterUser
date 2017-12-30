package project.doxuanvinh.usermaster.base.uitls;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.number.OrderingComparison.greaterThan;

/**
 * Created by Do Xuan Vinh on 24/03/2017.
 */

public class RecyclerViewUtil {
    public static class NotEmptyAssertion implements ViewAssertion {

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            ViewMatchers.assertThat(adapter.getItemCount(), Matchers.greaterThan(0));
        }
    }

    public static class EmptyViewAssertion implements ViewAssertion {

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

        }
    }

    public static Matcher<View> notEmpty() {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is not empty");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!RecyclerView.class.isInstance(view)) return false;
                RecyclerView recyclerView = (RecyclerView) view;
                int itemCount = recyclerView.getAdapter().getItemCount();
                return itemCount > 0;
            }
        };
    }


    public static void randomClick(int recyclerId) {
        RecyclerViewItemCountAssertion recyclerViewAssert = new RecyclerViewItemCountAssertion(greaterThan(0));
        onView(allOf(withId(recyclerId), isDisplayed())).check(recyclerViewAssert);
        onView(allOf(withId(recyclerId), isDisplayed()))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(recyclerViewAssert.getRandomItemPosition(), click()));

    }

    public static void randomClick(Matcher<View> recyclerView) {
        RecyclerViewItemCountAssertion recyclerViewAssert = new RecyclerViewItemCountAssertion(greaterThan(0));
        onView(recyclerView).check(recyclerViewAssert);
        onView(recyclerView)
                .perform(RecyclerViewActions.actionOnItemAtPosition(recyclerViewAssert.getRandomItemPosition(), click()));

    }
}
