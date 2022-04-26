package com.example.bytechallengue

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher

@RunWith(AndroidJUnit4ClassRunner::class)

class MainActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkActivityVisibility(){
        onView(withId(R.id.layout_main)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerView(){
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }



}