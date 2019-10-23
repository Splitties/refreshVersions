/*
 *
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.android.smallvictories


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.widget.EditText
import android.widget.TextView
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun tappingOnTitleOpensEditDialog() {
        onView(withId(R.id.textVictoryTitle))
            .perform(click())

        onView(withId(R.id.alertTitle))
            .check(matches(isDisplayed()))

        onView(withId(android.R.id.button2))
            .perform(click())
    }

    @Test
    fun editingDialogUpdatesTitle() {
        onView(withId(R.id.textVictoryTitle))
            .perform(click())

        val newTitle = "Made the bed"
        onView(instanceOf(EditText::class.java))
            .perform(clearText())
            .perform(typeText(newTitle))

        onView(withText(R.string.dialog_ok))
            .perform(click())

        onView(allOf(withId(R.id.textVictoryTitle), withText(newTitle)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun editingTitleDoesntChangeCount() {
        // 1
        onView(withId(R.id.fab))
            .perform(click())
        // 2
        onView(withId(R.id.textVictoryTitle))
            .perform(click())
        val newTitle = "Made the bed"
        onView(instanceOf(EditText::class.java))
            .perform(clearText())
            .perform(typeText(newTitle))
        onView(withText(R.string.dialog_ok))
            .perform(click())

        // 3
        onView(allOf(withId(R.id.textVictoryCount), withText("0")))
            .check(doesNotExist())
    }

    @Test
    fun incrementingVictoryCountUpdatesCountView() {
        val previousCountString = rule.activity.findViewById<TextView>(R.id.textVictoryCount).text.toString()
        val previousCount = if (previousCountString.isBlank()) 0 else previousCountString.toInt()

        onView(withId(R.id.fab))
            .perform(click())

        onView(allOf(withId(R.id.textVictoryCount), withText((previousCount + 1).toString())))
            .check(matches(isDisplayed()))
    }
}
