package project.doxuanvinh.usermaster.base.uitls;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.espresso.AmbiguousViewMatcherException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import project.doxuanvinh.usermaster.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Do Xuan Vinh on 24/03/2017.
 */

public class UtilsTest {

    /*
     * when click any item to open a recycler view
     * check recycler view is loaded or not
     */
//    public static void clickCheckRecycler(ViewInteraction viewClick, String classContainRecycler) {
//        viewClick.perform(click());
//        onView(allOf(withText(R.string.empty_error), withClassName(is(classContainRecycler)))).check(doesNotExist());
//    }

    /*
     * check view satisfied with input text or not
     */
    public static void checkIdWithText(@IdRes int id, String text) {
        try {
            onView(allOf(withId(id), isDisplayed())).check(matches(withText(text)));
        }catch (NoMatchingViewException e){
            onView(withId(id)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(withText(text)));
        }

    }

    /*
     * get current activity of current view
     */
    public static Activity getActivity() {
        final Activity[] currentActivity = new Activity[1];
        onView(allOf(withId(android.R.id.content), isDisplayed())).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view.getContext() instanceof Activity) {
                    Activity activity1 = ((Activity) view.getContext());
                    currentActivity[0] = activity1;
                }
            }
        });
        return currentActivity[0];
    }


    /**
     * find toolbar with title
     */
    public static Matcher<View> findToolbarWithTitle(CharSequence title) {
        return allOf(isAssignableFrom(Toolbar.class), withChild(withChild(allOf(isAssignableFrom(TextView.class), withText(title.toString())))));
    }

    public static Matcher<View> findToolbarWithTitle(@StringRes int title) {
        return allOf(isAssignableFrom(Toolbar.class), withChild(withChild(allOf(isAssignableFrom(TextView.class), withText(title)))));
    }

    /**
     * click back in toolbar with title
     */
    public static ViewInteraction clickBackToolbar(CharSequence title) {
        return onView(allOf(withClassName(is("android.widget.ImageButton")), withParent(findToolbarWithTitle(title)))).perform(click());
    }

    /**
     * click to Text display
     */
    public static ViewInteraction clickToTextView(@StringRes int resId) {
        return onView(allOf(withText(resId), isDisplayed())).perform(click());

    }

    /**
     * click to hind display
     */
    public static ViewInteraction clickWithHint(@StringRes int resId) {
        return onView(allOf(withHint(resId), isDisplayed())).perform(click());
    }

    /**
     * click with ID
     */
    public static ViewInteraction clickWithId(@IdRes int Id) {
        return onView(allOf(withId(Id), isDisplayed())).perform(click());
    }

//    public static void login(String username, String password) {
//        ViewInteraction etUsername = onView(
//                allOf(withId(R.id.et_email), isDisplayed()));
//        etUsername.perform(replaceText(username), closeSoftKeyboard());
//
//        ViewInteraction etPassword = onView(
//                allOf(withId(R.id.et_password), isDisplayed()));
//        etPassword.perform(replaceText(password), closeSoftKeyboard(), pressImeActionButton());
//    }

//    public static Matcher<View> withTaskViewName(final String expected) {
//        return new TypeSafeMatcher<View>() {
//            @Override
//            protected boolean matchesSafely(View item) {
//                if(item != null && item.findViewById(R.id.item_subtitle) != null) {
//                    TextView taskName = (TextView) item.findViewById(R.id.item_subtitle);
//                    if(TextUtils.isEmpty(taskName.getText())) {
//                        return false;
//                    } else {
//                        return taskName.getText().equals(expected);
//                    }
//                } else {
//                    return false;
//                }
//            }
//
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Looked for " + expected + " in the task_item.xml file");
//            }
//        };
//    }
    /*
     * get a view that sibling with other view
     * useful for customer view
     */
    public static ViewInteraction getViewInCustomView(@StringRes int textInSibling, @IdRes int idViewTarget, @IdRes int idViewDirect){
        Matcher<View> siblingView;
        ViewInteraction expectView;

        try {
            onView(allOf(withId(idViewDirect),withText(textInSibling))).perform(scrollTo());
            siblingView = allOf(withText(textInSibling), isDisplayed());
            expectView = onView(allOf(withId(idViewTarget),hasSibling(siblingView)));
        }catch (AmbiguousViewMatcherException e){
            siblingView = allOf(withText(textInSibling), isDisplayed());
            expectView = onView(allOf(withId(idViewTarget),hasSibling(siblingView)));
        }

        return expectView;
    }
}
