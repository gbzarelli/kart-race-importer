package br.com.helpdev.race.domain.race;

import java.time.LocalTime;

public class LapInfos {

    private final LocalTime time;
    private final LocalTime lapTime;
    private final Float avgSpeed;

    public LapInfos(final LocalTime time, final LocalTime lapTime, final Float avgSpeed) {
        this.time = time;
        this.lapTime = lapTime;
        this.avgSpeed = avgSpeed;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalTime getLapTime() {
        return lapTime;
    }

    public boolean bestOf(final LapInfos lapInfos) {
        return lapInfos == null || lapTime.toNanoOfDay() < lapInfos.lapTime.toNanoOfDay();
    }

    public Float getAvgSpeed() {
        return avgSpeed;
    }

    @Override
    public String toString() {
        return "LapInfos{" +
                "time=" + time +
                ", lapTime=" + lapTime +
                ", avgSpeed=" + avgSpeed +
                '}';
    }
}
