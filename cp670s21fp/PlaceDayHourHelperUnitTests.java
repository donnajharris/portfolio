package ca.wlu.cp670.group2.dinearound.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.wlu.cp670.group2.dinearound.model.DayOfWeek;
import ca.wlu.cp670.group2.dinearound.model.PlaceDayHour;
import ca.wlu.cp670.group2.dinearound.view.util.PlaceDayHourHelper;

public class PlaceDayHourHelperUnitTests {


    /* TESTS:     public List<PlaceDayHour> filterPlaceDayHourListByDay(List<PlaceDayHour> placeDayHourList, DayOfWeek nameOfDay) */
    //region filterPlaceDayHourListByDay tests

    @Test
    public void filterPlaceDayHourListByDay_filtersTuesday_true() {
        // arrange
        String regularOpen = "10:00";
        String regularClose = "22:00";
        String happyHoursStart = "17:00";
        String happyHoursEnd = "19:00";
        List<PlaceDayHour> unfilteredHoursList = new ArrayList<>();

        DayOfWeek today = DayOfWeek.Tuesday;
        String regularOpenTodayExpected = "11:00";
        String regularClosedTodayExpected = "21:00";
        String happyHourStartTodayExpected = "18:00";
        String happyHourEndTodayExpected = "20:00";

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            PlaceDayHour regularHours, happyHours;

            if (dayOfWeek == today) {
                regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected);
                happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected);
            } else {
                regularHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.REGULAR_HOURS, regularOpen, regularClose);
                happyHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.HAPPY_HOURS, happyHoursStart, happyHoursEnd);
            }

            unfilteredHoursList.add(regularHours);
            unfilteredHoursList.add(happyHours);
        }

        List<PlaceDayHour> expected = new ArrayList<>();
        expected.add(new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected));
        expected.add(new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected));

        // act
        List<PlaceDayHour> actual = PlaceDayHourHelper.getInstance().filterPlaceDayHourListByDay(unfilteredHoursList, today);

        // assert
        Assert.assertEquals(expected.size(), actual.size());

        for (PlaceDayHour placeDayHour : actual) {
            Assert.assertSame(placeDayHour.getDay(), today);
            if (placeDayHour.getHourType() == PlaceDayHour.REGULAR_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), regularOpenTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), regularClosedTodayExpected);
            } else if (placeDayHour.getHourType() == PlaceDayHour.HAPPY_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), happyHourStartTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), happyHourEndTodayExpected);
            }
        }
    }

    @Test
    public void filterPlaceDayHourListByDay_filtersTuesdayWithoutHappy_true() {
        // arrange
        String regularOpen = "10:00";
        String regularClose = "22:00";
        String happyHoursStart = "17:00";
        String happyHoursEnd = "19:00";
        List<PlaceDayHour> unfilteredHoursList = new ArrayList<>();

        DayOfWeek today = DayOfWeek.Tuesday;
        String regularOpenTodayExpected = "11:00";
        String regularClosedTodayExpected = "21:00";
        String happyHourStartTodayExpected = "";
        String happyHourEndTodayExpected = "";

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            PlaceDayHour regularHours, happyHours;

            if (dayOfWeek == today) {
                regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected);
                happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected);
            } else {
                regularHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.REGULAR_HOURS, regularOpen, regularClose);
                happyHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.HAPPY_HOURS, happyHoursStart, happyHoursEnd);
            }

            unfilteredHoursList.add(regularHours);
            unfilteredHoursList.add(happyHours);
        }

        List<PlaceDayHour> expected = new ArrayList<>();
        expected.add(new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected));
        expected.add(new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected));

        // act
        List<PlaceDayHour> actual = PlaceDayHourHelper.getInstance().filterPlaceDayHourListByDay(unfilteredHoursList, today);

        // assert
        Assert.assertEquals(expected.size(), actual.size());

        for (PlaceDayHour placeDayHour : actual) {
            Assert.assertSame(placeDayHour.getDay(), today);
            if (placeDayHour.getHourType() == PlaceDayHour.REGULAR_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), regularOpenTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), regularClosedTodayExpected);
            } else if (placeDayHour.getHourType() == PlaceDayHour.HAPPY_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), happyHourStartTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), happyHourEndTodayExpected);
            }
        }
    }

    @Test
    public void filterPlaceDayHourListByDay_filtersTuesdayWithoutRegular_true() {
        // arrange
        String regularOpen = "10:00";
        String regularClose = "22:00";
        String happyHoursStart = "17:00";
        String happyHoursEnd = "19:00";
        List<PlaceDayHour> unfilteredHoursList = new ArrayList<>();

        DayOfWeek today = DayOfWeek.Tuesday;
        String regularOpenTodayExpected = "";
        String regularClosedTodayExpected = "";
        String happyHourStartTodayExpected = "18:00";
        String happyHourEndTodayExpected = "20:00";

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            PlaceDayHour regularHours, happyHours;

            if (dayOfWeek == today) {
                regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected);
                happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected);
            } else {
                regularHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.REGULAR_HOURS, regularOpen, regularClose);
                happyHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.HAPPY_HOURS, happyHoursStart, happyHoursEnd);
            }

            unfilteredHoursList.add(regularHours);
            unfilteredHoursList.add(happyHours);
        }

        List<PlaceDayHour> expected = new ArrayList<>();
        expected.add(new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected));
        expected.add(new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected));

        // act
        List<PlaceDayHour> actual = PlaceDayHourHelper.getInstance().filterPlaceDayHourListByDay(unfilteredHoursList, today);

        // assert
        Assert.assertEquals(expected.size(), actual.size());

        for (PlaceDayHour placeDayHour : actual) {
            Assert.assertSame(placeDayHour.getDay(), today);
            if (placeDayHour.getHourType() == PlaceDayHour.REGULAR_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), regularOpenTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), regularClosedTodayExpected);
            } else if (placeDayHour.getHourType() == PlaceDayHour.HAPPY_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), happyHourStartTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), happyHourEndTodayExpected);
            }
        }
    }

    @Test
    public void filterPlaceDayHourListByDay_filtersTuesdayNoHours_true() {
        // arrange
        String regularOpen = "10:00";
        String regularClose = "22:00";
        String happyHoursStart = "17:00";
        String happyHoursEnd = "19:00";
        List<PlaceDayHour> unfilteredHoursList = new ArrayList<>();

        DayOfWeek today = DayOfWeek.Tuesday;
        String regularOpenTodayExpected = "";
        String regularClosedTodayExpected = "";
        String happyHourStartTodayExpected = "";
        String happyHourEndTodayExpected = "";

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            PlaceDayHour regularHours, happyHours;

            if (dayOfWeek == today) {
                regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected);
                happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected);
            } else {
                regularHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.REGULAR_HOURS, regularOpen, regularClose);
                happyHours = new PlaceDayHour(dayOfWeek, PlaceDayHour.HAPPY_HOURS, happyHoursStart, happyHoursEnd);
            }

            unfilteredHoursList.add(regularHours);
            unfilteredHoursList.add(happyHours);
        }

        List<PlaceDayHour> expected = new ArrayList<>();
        expected.add(new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, regularOpenTodayExpected, regularClosedTodayExpected));
        expected.add(new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, happyHourStartTodayExpected, happyHourEndTodayExpected));

        // act
        List<PlaceDayHour> actual = PlaceDayHourHelper.getInstance().filterPlaceDayHourListByDay(unfilteredHoursList, today);

        // assert
        Assert.assertEquals(expected.size(), actual.size());

        for (PlaceDayHour placeDayHour : actual) {
            Assert.assertSame(placeDayHour.getDay(), today);
            if (placeDayHour.getHourType() == PlaceDayHour.REGULAR_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), regularOpenTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), regularClosedTodayExpected);
            } else if (placeDayHour.getHourType() == PlaceDayHour.HAPPY_HOURS) {
                Assert.assertSame(placeDayHour.getOpen(), happyHourStartTodayExpected);
                Assert.assertSame(placeDayHour.getClose(), happyHourEndTodayExpected);
            }
        }
    }

    //endregion

    /* TESTS:      public boolean missingBothStartAndEndHours(String happyHourStart, String happyHourEnd)  */
    //region missingBothStartAndEndHours tests

    @Test
    public void missingBothStartAndEndHours_noHours_true() {

        String start = "";
        String end = "";

        boolean actual = PlaceDayHourHelper.getInstance().missingBothStartAndEndHours(start, end);

        Assert.assertTrue(actual);
    }

    @Test
    public void missingBothStartAndEndHours_hasBothHours_false() {

        String start = "15:00";
        String end = "22:00";

        boolean actual = PlaceDayHourHelper.getInstance().missingBothStartAndEndHours(start, end);

        Assert.assertFalse(actual);
    }

    @Test
    public void missingBothStartAndEndHours_hasStartHour_false() {

        String start = "15:00";
        String end = "";

        boolean actual = PlaceDayHourHelper.getInstance().missingBothStartAndEndHours(start, end);

        Assert.assertFalse(actual);
    }

    @Test
    public void missingBothStartAndEndHours_hasEndHour_false() {

        String start = "";
        String end = "21:00";

        boolean actual = PlaceDayHourHelper.getInstance().missingBothStartAndEndHours(start, end);

        Assert.assertFalse(actual);
    }


    //endregion

    /* TESTS:  public String[] getHourPairFromFilteredList(List<PlaceDayHour> placeDayHourList, int type) */
    //region  getHourPairFromFilteredList tests
    @Test
    public void getHourPairFromFilteredList_regularHours_full() {
        // arrange
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String[] expected = {expectedRegularOpen, expectedRegularClose};

        // act
        String[] actual = PlaceDayHourHelper.getInstance().getHourPairFromFilteredList(placeDayHourList, PlaceDayHour.REGULAR_HOURS);

        // assert
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getHourPairFromFilteredList_happyHours_full() {
        // arrange
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String[] expected = {expectedHappyHoursStart, expectedHappyHoursEnd};

        // act
        String[] actual = PlaceDayHourHelper.getInstance().getHourPairFromFilteredList(placeDayHourList, PlaceDayHour.HAPPY_HOURS);

        // assert
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getHourPairFromFilteredList_regularHours_empty() {
        // arrange
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String[] expected = {expectedRegularOpen, expectedRegularClose};

        // act
        String[] actual = PlaceDayHourHelper.getInstance().getHourPairFromFilteredList(placeDayHourList, PlaceDayHour.REGULAR_HOURS);

        // assert
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getHourPairFromFilteredList_happyHours_empty() {
        // arrange
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String[] expected = {expectedHappyHoursStart, expectedHappyHoursEnd};

        // act
        String[] actual = PlaceDayHourHelper.getInstance().getHourPairFromFilteredList(placeDayHourList, PlaceDayHour.HAPPY_HOURS);

        // assert
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getHourPairFromFilteredList_regularHours_none() {
        // arrange
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        String[] expected = {expectedRegularOpen, expectedRegularClose};

        // act
        String[] actual = PlaceDayHourHelper.getInstance().getHourPairFromFilteredList(placeDayHourList, PlaceDayHour.REGULAR_HOURS);

        // assert
        Assert.assertArrayEquals(expected, actual);
    }

    //endregion

    /* TESTS:     public boolean hasStartAndEndHours(List<PlaceDayHour> list) */
    //region hasStartAndEndHours tests

    @Test
    public void hasStartAndEndHours_fullSetOfHours_true() {
        String expectedRegularOpen = "10:00";
        String expectedRegularClose = "22:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        boolean actual = PlaceDayHourHelper.getInstance().hasStartAndEndHours(placeDayHourList);

        Assert.assertTrue(actual);
    }

    @Test
    public void hasStartAndEndHours_noHours_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        boolean actual = PlaceDayHourHelper.getInstance().hasStartAndEndHours(placeDayHourList);

        Assert.assertFalse(actual);
    }

    @Test
    public void hasStartAndEndHours_onlyHappyHours_true() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "19:00";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        boolean actual = PlaceDayHourHelper.getInstance().hasStartAndEndHours(placeDayHourList);

        Assert.assertTrue(actual);
    }

    @Test
    public void hasStartAndEndHours_onlyRegularHours_true() {
        String expectedRegularOpen = "17:00";
        String expectedRegularClose = "19:00";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        boolean actual = PlaceDayHourHelper.getInstance().hasStartAndEndHours(placeDayHourList);

        Assert.assertTrue(actual);
    }


    @Test
    public void hasStartAndEndHours_partialRegularHours_false() {
        String expectedRegularOpen = "17:00";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        boolean actual = PlaceDayHourHelper.getInstance().hasStartAndEndHours(placeDayHourList);

        Assert.assertFalse(actual);
    }

    @Test
    public void hasStartAndEndHours_partialHappyHours_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        boolean actual = PlaceDayHourHelper.getInstance().hasStartAndEndHours(placeDayHourList);

        Assert.assertFalse(actual);
    }

    @Test
    public void hasStartAndEndHours_partialHours_false() {
        String expectedRegularOpen = "";
        String expectedRegularClose = "19:00";
        String expectedHappyHoursStart = "17:00";
        String expectedHappyHoursEnd = "";
        List<PlaceDayHour> placeDayHourList = new ArrayList<>();
        DayOfWeek today = DayOfWeek.Tuesday;
        PlaceDayHour regularHours = new PlaceDayHour(today, PlaceDayHour.REGULAR_HOURS, expectedRegularOpen, expectedRegularClose);
        PlaceDayHour happyHours = new PlaceDayHour(today, PlaceDayHour.HAPPY_HOURS, expectedHappyHoursStart, expectedHappyHoursEnd);
        placeDayHourList.add(regularHours);
        placeDayHourList.add(happyHours);

        boolean actual = PlaceDayHourHelper.getInstance().hasStartAndEndHours(placeDayHourList);

        Assert.assertFalse(actual);
    }

    //endregion

}
