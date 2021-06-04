package com.dubizzle.classifieds.ui.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.dubizzle.classifieds.R
import com.dubizzle.classifieds.domain.presentation.classifiedslist.adapters.ClassifiedsListAdapter
import com.dubizzle.classifieds.domain.presentation.classifiedslist.fragments.ClassifiedsListFragment
import com.dubizzle.classifieds.domain.presentation.classifiedslist.fragments.ClassifiedsListFragmentDirections
import com.dubizzle.classifieds.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ClassifiedsListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickOnClassifiedsListItem() {

        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<ClassifiedsListFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.classifieds_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ClassifiedsListAdapter.ViewHolder>(
                0,
                click()
            )
        )
        val directions =
            ClassifiedsListFragmentDirections.actionClassifiedsListFragmentToClassifiedDetailsFragment(
                null
            )
        verify(navController).navigate(directions)
    }
}