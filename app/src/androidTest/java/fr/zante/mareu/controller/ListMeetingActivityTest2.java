package fr.zante.mareu.controller;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import static fr.zante.mareu.controller.MyViewAction.withIndex;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.zante.mareu.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListMeetingActivityTest2 {

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ListMeetingActivity.class);

    /**
     * check if able to filter the results by Meeting date and then by Meeting room
     */
    @Test
    public void listMeetingActivity_filterByMeetingDateOrMeetingRoom() {
        // add first meeting
        ViewInteraction addMeetingFloatingActionButton = onView(
                allOf(withId(R.id.activity_list_meeting_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        addMeetingFloatingActionButton.perform(click());

        // set topic
        ViewInteraction topicTextInputEditText = onView(
                allOf(withId(R.id.add_activity_textinputedittext_topic),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_activity_textinputlayout_topic),
                                        0),
                                0)));
        topicTextInputEditText.perform(scrollTo(), replaceText("test 01"), closeSoftKeyboard());

        // select members:
        ViewInteraction addMemberImageView = onView(
                allOf(withId(R.id.add_activity_add_member_to_list_button), withContentDescription("Ajouter un participant"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        addMemberImageView.perform(scrollTo(), click());

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

        // add the meeting:
        ViewInteraction addMeetingButton = onView(
                allOf(withId(R.id.add_meeting_add_button), withText("Reserver"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        addMeetingButton.perform(scrollTo(), click());

        // add second meeting
        ViewInteraction addMeetingFloatingActionButton2 = onView(
                allOf(withId(R.id.activity_list_meeting_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        addMeetingFloatingActionButton2.perform(click());

        //set topic
        ViewInteraction topicTextInputEditText2 = onView(
                allOf(withId(R.id.add_activity_textinputedittext_topic),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_activity_textinputlayout_topic),
                                        0),
                                0)));
        topicTextInputEditText2.perform(scrollTo(), replaceText("test 02"), closeSoftKeyboard());

        //set meeting room
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.add_activity_spinner_meeting_room),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(com.google.android.material.R.id.select_dialog_listview),
                        childAtPosition(
                                withId(com.google.android.material.R.id.contentPanel),
                                0)))
                .atPosition(1);
        linearLayout.perform(click());

        //select members:
        ViewInteraction addMemberImageView2 = onView(
                allOf(withId(R.id.add_activity_add_member_to_list_button), withContentDescription("Ajouter un participant"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        addMemberImageView2.perform(scrollTo(), click());

        onView(withIndex(withId(R.id.item_list_member_add_list_button), 0)).perform(click());
        onView(withIndex(withId(R.id.item_list_member_add_list_button), 1)).perform(click());
        onView(withIndex(withId(R.id.item_list_member_add_list_button), 2)).perform(click());

        ViewInteraction buildMemberListFloatingActionButton2 = onView(
                allOf(withId(R.id.add_member_activity_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        buildMemberListFloatingActionButton2.perform(click());

        //add the meeting
        ViewInteraction addMeetingButton2 = onView(
                allOf(withId(R.id.add_meeting_add_button), withText("Reserver"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        addMeetingButton2.perform(scrollTo(), click());

        // add third meeting
        ViewInteraction addMeetingFloatingActionButton3 = onView(
                allOf(withId(R.id.activity_list_meeting_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        addMeetingFloatingActionButton3.perform(click());

        //set topic:
        ViewInteraction topicTextInputEditText3 = onView(
                allOf(withId(R.id.add_activity_textinputedittext_topic),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_activity_textinputlayout_topic),
                                        0),
                                0)));
        topicTextInputEditText3.perform(scrollTo(), replaceText("test 03"), closeSoftKeyboard());

        //select members
        ViewInteraction addMemberImageView3 = onView(
                allOf(withId(R.id.add_activity_add_member_to_list_button), withContentDescription("Ajouter un participant"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        addMemberImageView3.perform(scrollTo(), click());

        onView(withIndex(withId(R.id.item_list_member_add_list_button), 0)).perform(click());
        onView(withIndex(withId(R.id.item_list_member_add_list_button), 1)).perform(click());
        onView(withIndex(withId(R.id.item_list_member_add_list_button), 2)).perform(click());

        ViewInteraction buildMemberListFloatingActionButton3 = onView(
                allOf(withId(R.id.add_member_activity_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        buildMemberListFloatingActionButton3.perform(click());

        // add the meeting:
        ViewInteraction addMeetingButton3 = onView(
                allOf(withId(R.id.add_meeting_add_button), withText("Reserver"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        addMeetingButton3.perform(scrollTo(), click());

        // options menu :
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        // filter by date test:
        ViewInteraction byMeetingDateTextView = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Recherche par date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        byMeetingDateTextView.perform(click());

        // select a date :
        ViewInteraction meetingDateTextView = onView(
                allOf(withId(androidx.transition.R.id.title), withText("9/3/2023"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        meetingDateTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.activity_list_meeting_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.check(matches(hasChildCount(3)));

        // options menu:
        ViewInteraction overflowMenuButton3 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton3.perform(click());

        //filter by meeting room test:
        ViewInteraction byMeetingRoomTextView = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Recherche par salle"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        byMeetingRoomTextView.perform(click());

        //select meeting room
        ViewInteraction meetingRoomTextView = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Reunion A"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        meetingRoomTextView.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.activity_list_meeting_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));
        recyclerView3.check(matches(hasChildCount(2)));

        // options menu
        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        // show all test :
        ViewInteraction showAllTextView = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Afficher tout"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        showAllTextView.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.activity_list_meeting_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));
        recyclerView2.check(matches(hasChildCount(3)));

        // options menu:
        ViewInteraction overflowMenuButton4 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton4.perform(click());

        //filter by meeting room test:
        ViewInteraction byMeetingRoomTextView2 = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Recherche par salle"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        byMeetingRoomTextView2.perform(click());

        // select meeting room
        ViewInteraction meetingRoomTextView2 = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Reunion B"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        meetingRoomTextView2.perform(click());

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.activity_list_meeting_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView4.check(matches(isDisplayed()));
        recyclerView4.check(matches(hasChildCount(1)));
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
