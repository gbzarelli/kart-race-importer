package br.com.helpdev.race.commons.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeUtilsTest {

    @Test
    void formatNanoTimeToTime() {
        final var of = LocalTime.of(1, 10, 20, 422);
        final var timeFormatted = TimeUtils.formatNanoTime(of.toNanoOfDay());
        assertEquals(of.toString(), timeFormatted);
    }

    @Test
    void formatNegativeNanoTimeToTime() {
        final var of = LocalTime.of(1, 55, 20, 422);
        final var time = of.toNanoOfDay() * -1;
        final var timeFormatted = TimeUtils.formatNanoTime(time);
        assertEquals("-" + of.toString(), timeFormatted);
    }
}