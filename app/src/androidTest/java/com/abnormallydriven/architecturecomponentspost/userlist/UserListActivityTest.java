package com.abnormallydriven.architecturecomponentspost.userlist;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.abnormallydriven.architecturecomponentspost.LiveDataTestUtil;
import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.RecyclerViewMatcher;
import com.abnormallydriven.architecturecomponentspost.common.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.common.di.DaggerInjector;
import com.abnormallydriven.architecturecomponentspost.common.di.DaggerTestApplicationComponent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserListActivityTest {

    @Rule
    public ActivityTestRule<UserListActivity> userListActivityActivityTestRule =
            new ActivityTestRule<>(UserListActivity.class, true, false);

    private UserDao userDao;

    @Before
    public void setup(){
        userDao = ((DaggerTestApplicationComponent) DaggerInjector.getAppComponent())
                .measurementDatabase()
                .userDao();
        setupFakeUsers();
    }

    @After
    public void tearDown(){
        User[] userList = LiveDataTestUtil.getValue(userDao.getAllUsers());

        for (int i = 0; i < userList.length; i++) {
            userDao.deleteUser(userList[i]);
        }
    }



    @Test
    public void userListActivityShouldDisplayListOfUsers(){
        userListActivityActivityTestRule.launchActivity(new Intent(InstrumentationRegistry.getTargetContext(), UserListActivity.class));

        User[] userList = LiveDataTestUtil.getValue(userDao.getAllUsers());
        onView(withId(R.id.user_list_recycler_view)).check(matches(isDisplayed()));

        int adapterItemCount = userListActivityActivityTestRule.getActivity().userListAdapter.getItemCount();

        assertEquals(userList.length, adapterItemCount);
        onView(new RecyclerViewMatcher(R.id.user_list_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Brett Tucker"))));

        onView(withId(R.id.add_user_fab)).check(matches(isDisplayed()));
    }



    private void setupFakeUsers(){
        userDao.inserUser(new User("Brett", "Tucker", 29, "M"));
        userDao.inserUser(new User("Decca", "Zelner", 36, "F"));
        userDao.inserUser(new User("Chen", "Halsted", 50, "M"));
        userDao.inserUser(new User("Ludovika", "Ramplee", 44, "F"));
        userDao.inserUser(new User("Clementia", "Chidwick", 24, "F"));
        userDao.inserUser(new User("Eartha", "Jeffrey", 51, "F"));
        userDao.inserUser(new User("Adriano", "Aylmer", 26, "M"));
        userDao.inserUser(new User("Jo", "Chaimson", 45, "F"));
        userDao.inserUser(new User("Livvy", "Sisley", 35, "F"));
        userDao.inserUser(new User("Brok", "Clapham", 53, "M"));
        userDao.inserUser(new User("Farley", "Dagless", 26, "M"));
        userDao.inserUser(new User("Royall", "Flounders", 21, "F"));
    }

}
