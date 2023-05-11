package ca.wlu.cp670.group2.dinearound.view.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.wlu.cp670.group2.dinearound.R;

public class TimeHelper {

    @SuppressLint("StaticFieldLeak")
    private static TimeHelper instance = null;
    @SuppressLint("StaticFieldLeak")
    private Activity activity;

    private final static DateFormat dayNameFormatter = new SimpleDateFormat("EEEE", Locale.forLanguageTag("en-CA"));
    private final static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.forLanguageTag("en-CA"));

    private TimeHelper() {
    }

    public static TimeHelper getInstance() {
        if (instance == null) {
            instance = new TimeHelper();
        }
        return instance;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getCurrentDayOfTheWeek() {
        Date currentDeviceTime = Calendar.getInstance().getTime();
        return dayNameFormatter.format(currentDeviceTime);
    }

    public String getCurrentTime() {
        Date currentDeviceTime = Calendar.getInstance().getTime();
        return timeFormatter.format(currentDeviceTime);
    }

    public String getTimeStringFromDate(Date date) {
        return timeFormatter.format(date);
    }

    public Date getDateFromTimeString(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {

            try {
                return timeFormatter.parse(dateString);
            } catch (ParseException | NullPointerException e) {
                Log.e("TimeHelper", "getDateFromTimeString() error:\n" + Arrays.toString(e.getStackTrace()));
            }
        }

        return null;
    }


    // Reference: https://stackoverflow.com/questions/2309558/time-comparison
    public boolean isBetweenHours(String userTime, String startTime, String endTime) {

        try {
            Date startDate = timeFormatter.parse(startTime);
            Date endDate = timeFormatter.parse(endTime);

            assert startDate != null;
            assert endDate != null;

            // validate the range
            if (hasValidTimeRange(startDate, endDate)) {

                // adjust the range to address boundaries (e.g., open at 11:00 exactly, not just 11:01)
                startDate = adjustDateForBoundary(startDate, -1);
                endDate = adjustDateForBoundary(endDate, +1);

                // check the user date provided
                Date userDate = timeFormatter.parse(userTime);
                assert userDate != null;

                if (userDate.after(startDate) && userDate.before(endDate)) {
                    return true;
                }
            }

        } catch (ParseException | NullPointerException e) {
            Log.e("TimeHelper", "isBetweenHours() error:\n" + Arrays.toString(e.getStackTrace()));
        }

        return false;
    }


    // Reference: https://www.baeldung.com/java-add-hours-date
    public Date adjustDateForBoundary(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }


    public boolean hasValidTimeRange(Date startDate, Date endDate) {
        assert startDate != null;
        assert endDate != null;

        return !endDate.before(startDate);
    }


    public boolean isApproachingEndBoundary(String currentTime, String endBoundary, int warningBeforeClosing) {

        Date endDate = getDateFromTimeString(endBoundary);
        Date warningDate = adjustDateForBoundary(endDate, -warningBeforeClosing);
        String warningBoundary = getTimeStringFromDate(warningDate);
        return this.isBetweenHours(currentTime, warningBoundary, endBoundary);
    }

    public TextWatcher getTimeWatcherValidation(EditText editText) {
        return new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && !isValidTime(s.toString())) {
                    editText.setError(activity.getString(R.string.invalid_time_24_hour_format));
                } else {
                    editText.setError(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };
    }

    private boolean isValidTime(String time) {
        // Regex to check valid time in 12-hour format.
        String regexPattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";//""(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
        Pattern compiledPattern = Pattern.compile(regexPattern);
        if (time == null) {
            return false;
        }
        Matcher m = compiledPattern.matcher(time);
        return m.matches();
    }
}
