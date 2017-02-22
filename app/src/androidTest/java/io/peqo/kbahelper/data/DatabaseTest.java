package io.peqo.kbahelper.data;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.peqo.kbahelper.db.DbHelper;

import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private DbHelper dbHelper;

    @Before
    public void setup() {
        dbHelper = new DbHelper(InstrumentationRegistry.getTargetContext());
        dbHelper.open();
    }

    @After
    public void finish() {
        dbHelper.close();
    }

    @Test
    public void testPreCondition() {
        assertNotNull(dbHelper);
    }

}
