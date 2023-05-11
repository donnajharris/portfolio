package ca.wlu.cp670.group2.dinearound.view.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.stream.Collectors;

import ca.wlu.cp670.group2.dinearound.model.DayOfWeek;
import ca.wlu.cp670.group2.dinearound.model.PlaceDayHour;

import static ca.wlu.cp670.group2.dinearound.model.PlaceDayHour.HAPPY_HOURS;
import static ca.wlu.cp670.group2.dinearound.model.PlaceDayHour.REGULAR_HOURS;

public class PlaceDayHourHelper {

    private static PlaceDayHourHelper instance = null;
    public static final int OPEN = 0;
    public static final int CLOSE = 1;
    public static final int START = OPEN;
    public static final int END = CLOSE;
    private static final int WARNING_BEFORE_CLOSING_TIME = 30; // in minutes
    protected TimeHelper timeHelper = TimeHelper.getInstance();

    private PlaceDayHourHelper() {
    }

    public static PlaceDayHourHelper getInstance() {
        if (instance == null) {
            instance = new PlaceDayHourHelper();
        }
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<PlaceDayHour> filterPlaceDayHourListByDay(List<PlaceDayHour> placeDayHourList, DayOfWeek nameOfDay) {

        return placeDayHourList.stream()
                .filter((placeDayHour) -> placeDayHour.getDay().equals(nameOfDay))
                .collect(Collectors.toList());
    }

    public boolean happyHourIsNow(List<PlaceDayHour> placeDayHourList, String currentTime) {

        String[] hours = getHourPairFromFilteredList(placeDayHourList, HAPPY_HOURS);

        // Check there are start AND end times for happy hour
        if (missingBothStartAndEndHours(hours[START], hours[END]))
            return false;

        // Check if current time is in the period
        return timeHelper.isBetweenHours(currentTime, hours[START], hours[END]);
    }

    public boolean isClosingSoon(List<PlaceDayHour> placeDayHourList, String currentTime) {

        String[] hours = getHourPairFromFilteredList(placeDayHourList, REGULAR_HOURS);

        // Check there are start AND end times for regular hours
        if (missingBothStartAndEndHours(hours[OPEN], hours[CLOSE]))
            return false;

        // Check if current time is in the period
        boolean placeIsOpen = timeHelper.isBetweenHours(currentTime, hours[OPEN], hours[CLOSE]);

        if (placeIsOpen) {
            return timeHelper.isApproachingEndBoundary(currentTime, hours[CLOSE], WARNING_BEFORE_CLOSING_TIME);
        }

        return false;
    }

    public boolean isOpenNow(List<PlaceDayHour> placeDayHourList, String currentTime) {
        String[] hours = getHourPairFromFilteredList(placeDayHourList, REGULAR_HOURS);

        // Check there are start AND end times for regular hours
        if (missingBothStartAndEndHours(hours[OPEN], hours[CLOSE]))
            return false;

        // Check if current time is in the period
        return timeHelper.isBetweenHours(currentTime, hours[OPEN], hours[CLOSE]);
    }

    public boolean missingBothStartAndEndHours(String happyHourStart, String happyHourEnd) {
        return happyHourStart.isEmpty() && happyHourEnd.isEmpty();
    }

    public boolean hasStartAndEndHours(List<PlaceDayHour> list) {

        for (PlaceDayHour placeDayHour : list) {
            if (!placeDayHour.getOpen().isEmpty() && !placeDayHour.getClose().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public String[] getHourPairFromFilteredList(List<PlaceDayHour> placeDayHourList, int type) {
        String[] hours = {"", ""};

        for (PlaceDayHour placeDayHour : placeDayHourList) {
            if (placeDayHour.getHourType() == type) {
                if (!placeDayHour.getOpen().equals("") && !placeDayHour.getClose().equals("")) {
                    hours[0] = placeDayHour.getOpen();
                    hours[1] = placeDayHour.getClose();
                }
            } // else ignore non-type
        }

        return hours;
    }

    @SuppressWarnings("unused")
    public String printAllHours(String heading, List<PlaceDayHour> placeDayHourList) {
        StringBuilder str = new StringBuilder();
        str.append(heading);
        str.append("\n");

        for (PlaceDayHour placeDayHour : placeDayHourList) {
            str.append(placeDayHour);
            str.append("\n");
        }
        str.append("\n");
        return str.toString();
    }

}
