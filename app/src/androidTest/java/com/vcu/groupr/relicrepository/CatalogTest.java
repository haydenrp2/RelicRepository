package com.vcu.groupr.relicrepository;

import android.support.test.espresso.DataInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CatalogTest {
    @Rule
    public ActivityTestRule<Catalog> mActivityRule = new ActivityTestRule<>(Catalog.class);

    private void replace(int id, String str){
        onView(withId(id)).perform(replaceText(str));
    }

    @Before
    public void addItem(){
        onView(withId(R.id.addButton)).perform(click());
        replace(R.id.artifactName,"name");
        replace(R.id.artifactType,"type");
        replace(R.id.description,"description");
        replace(R.id.artifactAge,"0");
        replace(R.id.locationFound,"location");
        replace(R.id.date,"date");
        replace(R.id.price,"1.23");
        replace(R.id.url,"url.com");
        onView(withId(R.id.submitButton)).perform(click());
    }

    @Test
    public void test1ListClick() throws InterruptedException{
        Thread.sleep(5000);
        DataInteraction dataInteraction = onData(anything()).inAdapterView(withId((R.id.listView))).atPosition(0);
        dataInteraction.perform(click());
        onView(withId(R.id.textView1)).check(matches(withText("t")));
    }
}
