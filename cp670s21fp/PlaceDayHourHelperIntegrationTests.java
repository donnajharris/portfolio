package ca.wlu.cp670.group2.dinearound.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.wlu.cp670.group2.dinearound.model.DayOfWeek;
import ca.wlu.cp670.group2.dinearound.model.PlaceDayHour;
import ca.wlu.cp670.group2.dinearound.view.util.PlaceDayHourHelper;

public class PlaceDayHourHelperIntegrationTests {

    /* TESTS:     public boolean happyHourIsNow(List<PlaceDayHour> placeDayHourList, String currentTime)  */
    //region happyHourIsNow tests

    @Test
    public void happyHourIsNow_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "17:30";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void happyHourIsNow_noRegularHours_true() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        // testing around an unusual user edge case, as it is expected to work right now
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "17:30";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void happyHourIsNow_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void happyHourIsNow_missingStartHappy_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "18:00";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void happyHourIsNow_missingEndHappy_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "18:00";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void happyHourIsNow_happyHoursBlank_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void happyHourIsNow_allHoursBlank_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void happyHourIsNow_outsideOfOpenButInHappy_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "16:00";
        // testing around an unusual user edge case, as it is expected to work right now
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "18:45";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    //endregion

    /* TESTS:     public boolean isClosingSoon(List<PlaceDayHour> placeDayHourList, String currentTime)  */
    //region isClosingSoon tests

    @Test
    public void isClosingSoon_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "21:45";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isClosingSoon_noHappyHours_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "21:45";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isClosingSoon_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isClosingSoon_missingOpenHours_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isClosingSoon_missingCLosingHours_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isClosingSoon_regularHoursBlank_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isClosingSoon_allHoursBlank_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isClosingSoon_outsideOfOpenButWithinHappy_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "16:00";
        // testing around an unusual user edge case, as it is expected to work right now
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "18:45";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    //endregion

    /* TESTS:     public boolean isOpenNow(List<PlaceDayHour> placeDayHourList, String currentTime)  */
    //region isOpenNow tests

    @Test
    public void isOpenNow_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "15:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isOpenNow_noHappyHours_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "15:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isOpenNow_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "05:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isOpenNow_missingOpenHours_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isOpenNow_missingClosingHours_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isOpenNow_regularHoursBlank_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isOpenNow_allHoursBlank_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "20:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isOpenNow_outsideOfOpenButWithinHappy_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "16:00";
        // testing around an unusual user edge case, as it is expected to work right now
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "18:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    //endregion

    /* BOUNDARY TESTS */
    //region Boundary Testing

    /* happyHourIsNow tests */
    @Test
    public void happyHourIsNow_atUpperBoundaryStart_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "17:00";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void happyHourIsNow_beforeUpperBoundaryStart_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "16:59";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void happyHourIsNow_afterUpperBoundaryStart_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "17:01";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void happyHourIsNow_atLowerBoundaryEnd_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "19:00";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void happyHourIsNow_beforeLowerBoundaryEnd_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "18:59";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void happyHourIsNow_afterLowerBoundaryEnd_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "19:01";

        boolean actual = PlaceDayHourHelper.getInstance().happyHourIsNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }


    /* isClosingSoon tests */
    @Test
    public void isClosingSoon_atHalfHourBefore_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "21:30";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isClosingSoon_atHalfHourMinusOneMinute_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "21:29";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isClosingSoon_atHalfHourMinusOneMinute_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "21:45";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isClosingSoon_atLowerBoundaryClose_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "22:00";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isClosingSoon_beforeLowerBoundaryClose_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "21:59";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isClosingSoon_afterLowerBoundaryClose_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "22:01";

        boolean actual = PlaceDayHourHelper.getInstance().isClosingSoon(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }


    /* isOpenNow tests */
    @Test
    public void isOpenNow_atUpperBoundaryOpen_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "10:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isOpenNow_beforeUpperBoundaryOpen_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "09:59";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    @Test
    public void isOpenNow_afterUpperBoundaryOpen_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "10:01";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isOpenNow_atLowerBoundaryClose_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "22:00";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isOpenNow_beforeLowerBoundaryClose_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "21:59";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertTrue(actual);
    }

    @Test
    public void isOpenNow_afterLowerBoundaryClose_false() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Wednesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String currentTime = "22:01";

        boolean actual = PlaceDayHourHelper.getInstance().isOpenNow(placeDayHourList, currentTime);
        Assert.assertFalse(actual);
    }

    //endregion

}
