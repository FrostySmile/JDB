package com.yihen.jdb;

import android.test.ActivityInstrumentationTestCase2;
import com.yihen.jdb.business.IUserLab;
import com.yihen.jdb.business.UserImpl;
import com.yihen.jdb.ui.activity.StartActivity;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.yihen.jdb.ui.activity.StartActivityTest \
 * com.yihen.jdb.tests/android.test.InstrumentationTestRunner
 */
public class StartActivityTest extends ActivityInstrumentationTestCase2<StartActivity> {

    public StartActivityTest() {
        super("com.yihen.jdb", StartActivity.class);
    }

}
