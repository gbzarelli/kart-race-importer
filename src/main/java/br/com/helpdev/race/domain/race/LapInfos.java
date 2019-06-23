package br.com.helpdev.race.domain.race;

import java.time.LocalTime;

public class LapInfos {

    private LocalTime time;
    private LocalTime lapTime;
    private Float avgSpeed;

    public LapInfos(LocalTime time, LocalTime lapTime, Float avgSpeed) {
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

    public boolean bestOf(LapInfos lapInfos) {
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
