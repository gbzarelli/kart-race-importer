package br.com.helpdev.race.shared.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private TimeUtils() {
    }

    public static String formatNanoTime(long timeInNano) {
        StringBuilder ret = new StringBuilder();
        if (timeInNano < 0) {
            timeInNano *= -1;
            ret.append("-");
        }
        LocalTime localTime = LocalTime.ofNanoOfDay(timeInNano);
        ret.append(localTime.toString());
        return ret.toString();
    }

    public static long getDiffInNano(LocalTime localTime_1, LocalTime localTime_2) {
        return localTime_1.toNanoOfDay() - localTime_2.toNanoOfDay();
    }

    public static LocalTime convertToLocalTime(String timeString, DateTimeFormatter pattern) {
        String newTimeString = timeString;
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
