package project.doxuanvinh.usermaster.user.usercase;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import project.doxuanvinh.usermaster.R;
import project.doxuanvinh.usermaster.base.uitls.RecyclerViewItemCountAssertion;
import project.doxuanvinh.usermaster.base.uitls.UtilsTest;
import project.doxuanvinh.usermaster.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;

/**
 * Created by Do Xuan Vinh on 30/12/2017.
 */
@RunWith(AndroidJUnit4.class)
public class UserTest {
    private static final String USERNAME_EDIT = "Vinh TEST EDIT";
    private static final String USERNAME = "Vinh TEST";
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void check1_addUser() {
        // get total item in list
        RecyclerViewItemCountAssertion recyclerViewAssert = new RecyclerViewItemCountAssertion(greaterThanOrEqualTo(0));
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(recyclerViewAssert);
        int countList = recyclerViewAssert.getTotalItem();

        //click add user
        UtilsTest.clickWithId(R.id.img_add);
        //fill user name
        ViewInteraction editText = onView(allOf(withId(R.id.edt_add_user), isDisplayed()));
        editText.perform(replaceText(USERNAME));
        UtilsTest.clickWithId(R.id.tv_ok);

        //check list view if number of user > start => pass
        recyclerViewAssert = new RecyclerViewItemCountAssertion(greaterThan(countList));
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(recyclerViewAssert);

    }

    @Test
    public void check2_editUser() {
        Matcher<View> textUserName = allOf(withId(R.id.tv_username), withText(USERNAME), isDisplayed());
        onView(allOf(withId(R.id.layout_more), hasSibling(textUserName), isDisplayed())).perform(click());

        //click edit user
        UtilsTest.clickWithId(R.id.tv_edit);
        //fill user name
        ViewInteraction editText = onView(allOf(withId(R.id.edt_add_user), isDisplayed()));
        editText.perform(replaceText(USERNAME_EDIT));
        UtilsTest.clickWithId(R.id.tv_ok);

        allOf(withId(R.id.tv_username), withText(USERNAME_EDIT)).matches(isDisplayed());

    }

    @Test
    public void check_deleteUser() {
        // get total item in list
        RecyclerViewItemCountAssertion recyclerViewAssert = new RecyclerViewItemCountAssertion(greaterThanOrEqualTo(0));
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(recyclerViewAssert);
        int countList = recyclerViewAssert.getTotalItem();

        Matcher<View> textUserName = allOf(withId(R.id.tv_username), withText(USERNAME_EDIT), isDisplayed());
        onView(allOf(withId(R.id.layout_more), hasSibling(textUserName), isDisplayed())).perform(click());

        //click edit user
        UtilsTest.clickWithId(R.id.tv_delete);

        //check list view if number of user > start => pass
        recyclerViewAssert = new RecyclerViewItemCountAssertion(equalTo(countList - 1));
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(recyclerViewAssert);

    }
}
