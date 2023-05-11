package ca.wlu.cp670.group2.dinearound.util;

import org.junit.Test;

import ca.wlu.cp670.group2.dinearound.view.util.TimeHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeHelperUnitTests {

    @Test
    public void isBetweenHours_true() {
        String startTime = "16:00";
        String endTime = "18:00";

        String userTime = "17:00";

        boolean actual = TimeHelper.getInstance().isBetweenHours(userTime, startTime, endTime);
        assertTrue(actual);
    }

    @Test
    public void isBetweenHours_false_after() {
        String startTime = "16:00";
        String endTime = "18:00";

        String userTime = "20:00";

        boolean actual = TimeHelper.getInstance().isBetweenHours(userTime, startTime, endTime);
        assertFalse(actual);
    }

    @Test
    public void isBetweenHours_false_before() {
        String startTime = "16:00";
        String endTime = "18:00";

        String userTime = "12:00";

        boolean actual = TimeHelper.getInstance().isBetweenHours(userTime, startTime, endTime);
        assertFalse(actual);
    }

    @SuppressWarnings("redundant")
    @Test
    public void isBetweenHours_true_atStart() {
        String startTime = "16:00";
        String endTime = "18:00";

        // user time is startTime
        boolean actual = TimeHelper.getInstance().isBetweenHours(startTime, startTime, endTime);
        assertTrue(actual);
    }

    @Test
    public void isBetweenHours_true_atEnd() {
        String startTime = "16:00";
        String endTime = "18:00";

        // user time is endTime
        boolean actual = TimeHelper.getInstance().isBetweenHours(endTime, startTime, endTime);
        assertTrue(actual);
    }

    @Test
    public void isBetweenHours_false_atStartBoundary() {
        String startTime = "16:00";
        String endTime = "18:00";

        String userTime = "15:59";

        boolean actual = TimeHelper.getInstance().isBetweenHours(userTime, startTime, endTime);
        assertFalse(actual);
    }

    @Test
    public void isBetweenHours_false_atEndBoundary() {
        String startTime = "16:00";
        String endTime = "18:00";

        String userTime = "18:01";

        boolean actual = TimeHelper.getInstance().isBetweenHours(userTime, startTime, endTime);
        assertFalse(actual);
    }
}
