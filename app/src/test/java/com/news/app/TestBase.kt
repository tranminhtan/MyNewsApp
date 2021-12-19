package com.news.app

import androidx.annotation.CallSuper
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class TestBase {

    @Before
    @CallSuper
    open fun setup() {
        MockitoAnnotations.openMocks(this)
    }
}
