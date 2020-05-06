package br.com.helpdev.race.commons.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private TimeUtils() {
    }

    public static String formatNanoTime(final long inputTimeInNano) {
        var timeInNano = inputTimeInNano;
        final var ret = new StringBuilder();
        if (timeInNano < 0) {
            timeInNano *= -1;
            ret.append("-");
        }
        final var localTime = LocalTime.ofNanoOfDay(timeInNano);
        ret.append(localTime.toString());
        return ret.toString();
    }

    public static long getDiffInNano(final LocalTime localTime1,
                                     final LocalTime localTime2) {
        return localTime1.toNanoOfDay() - localTime2.toNanoOfDay();
    }

    public static LocalTime convertToLocalTime(final String timeString,
                                               final DateTimeFormatter pattern) {
        var newTimeString = timeString;
        if (timeString.length() == 8) {
            newTimeString = "00:0" + timeString;
        } else if (timeString.length() == 9) {
            newTimeString = "00:" + timeString;
        } else if (timeString.length() == 10) {
            newTimeString = "0" + timeString;
        }
        return LocalTime.from(pattern.parse(newTimeString));
    }
}
