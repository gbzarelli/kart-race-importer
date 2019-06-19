package br.com.helpdev.race.shared.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    @Test
    void formatNanoTimeToTime() {
        LocalTime of = LocalTime.of(1, 10, 20, 422);
        String timeFormatted = TimeUtils.formatNanoTime(of.toNanoOfDay());
        assertEquals(of.toString(), timeFormatted);
    }

    @Test
    void formatNegativeNanoTimeToTime() {
        LocalTime of = LocalTime.of(1, 55, 20, 422);
        long time = of.toNanoOfDay() * -1;
        String timeFormatted = TimeUtils.formatNanoTime(time);
        assertEquals("-" + of.toString(), timeFormatted);
    }
}