package fr.zante.mareu.controller;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import static fr.zante.mareu.controller.MyViewAction.withIndex;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.zante.mareu.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListMeetingActivityTest {

    private static int ITEMS_MEETING_COUNT = 0;

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ListMeetingActivity.class);


    /**
     * check if the list of meeting is empty
     */
    @Test
    public void listMeetingActivity_shouldBeEmpty() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.activity_list_meeting_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.check(matches(hasMinimumChildCount(ITEMS_MEETING_COUNT)));
    }

    /**
     * check if AddMeetingActivity is open
     */
    @Test
    public void addMeetingActivity_isDisplayed() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.activity_list_meeting_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                        withParent(withParent(withId(R.id.add_meeting_date_picker))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }

    /**
     * check if AddMemberActivity is open
     * check if the list of members is empty
     */
    @Test
    public void addMemberActivity_isDisplayed() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.activity_list_meeting_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction addMemberImageView = onView(
                allOf(withId(R.id.add_activity_add_member_to_list_button), withContentDescription("Ajouter un participant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        addMemberImageView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.add_member_activity_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.check(matches(hasMinimumChildCount(1)));
    }

    /**
     * should add a meeting to the list
     */
    @Test
    public void listMeetingActivity_shouldAddMeetingAndRemoveIt() {
        ViewInteraction addMeetingfloatingActionButton = onView(
                allOf(withId(R.id.activity_list_meeting_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        addMeetingfloatingActionButton.perform(click());

        ViewInteraction topicEditText = onView(
                allOf(withId(R.id.add_activity_textinputedittext_topic),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_activity_textinputlayout_topic),
                                        0),
                                0),
                        isDisplayed()));
        topicEditText.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction meetingRoomSpinner = onView(
                allOf(withId(R.id.add_activity_spinner_meeting_room),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        meetingRoomSpinner.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(com.google.android.material.R.id.select_dialog_listview),
                        childAtPosition(
                                withId(com.google.android.material.R.id.contentPanel),
                                0)))
                .atPosition(0);
        linearLayout.perform(click());

        ViewInteraction timeSlotSpinner = onView(
                allOf(withId(R.id.add_activity_spinner_time_slot),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        timeSlotSpinner.perform(click());

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(com.google.android.material.R.id.select_dialog_listview),
                        childAtPosition(
                                withId(com.google.android.material.R.id.contentPanel),
                                0)))
                .atPosition(0);
        linearLayout2.perform(click());

        ViewInteraction addMemberImageView = onView(
                allOf(withId(R.id.add_activity_add_member_to_list_button), withContentDescription("Ajouter un participant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        addMemberImageView.perform(click());

        onView(withIndex(withId(R.id.item_list_member_add_list_button), 0)).perform(click());
        onView(withIndex(withId(R.id.item_list_member_add_list_button), 1)).perform(click());
        onView(withIndex(withId(R.id.item_list_member_add_list_button), 2)).perform(click());

        ViewInteraction buildMemberListFloatingActionButton = onView(
                allOf(withId(R.id.add_member_activity_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        buildMemberListFloatingActionButton.perform(click());

        ViewInteraction memberRecyclerView = onView(
                allOf(withId(R.id.add_activity_recycler_view_meeting_member_list),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        memberRecyclerView.check(matches(isDisplayed()));
        memberRecyclerView.check(matches(hasMinimumChildCount(3)));

        ViewInteraction addMeetingButton = onView(
                allOf(withId(R.id.add_meeting_add_button), withText("Reserver"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        addMeetingButton.perform(click());

        ViewInteraction meetingListRecyclerView2 = onView(
                allOf(withId(R.id.activity_list_meeting_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        meetingListRecyclerView2.check(matches(isDisplayed()));
        meetingListRecyclerView2.check(matches(hasMinimumChildCount(1)));

        onView(withIndex(withId(R.id.item_list_meeting_delete_button), 0)).perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
