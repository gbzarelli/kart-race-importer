package br.com.helpdev.race.infrastructure.logfile.entities;

import java.time.LocalTime;


public class LapEntity {

    private final LocalTime time;
    private final PilotEntity pilot;
    private final Integer lapNumber;
    private final LocalTime lapTime;
    private final Float speedAvg;

    public LapEntity(final LocalTime time,
                     final PilotEntity pilot,
                     final Integer lapNumber,
                     final LocalTime lapTime,
                     final Float speedAvg) {
        this.time = time;
        this.pilot = pilot;
        this.lapNumber = lapNumber;
        this.lapTime = lapTime;
        this.speedAvg = speedAvg;
    }

    public LocalTime getTime() {
        return time;
    }

    public PilotEntity getPilot() {
        return pilot;
    }

    public Integer getLapNumber() {
        return lapNumber;
    }

    public LocalTime getLapTime() {
        return lapTime;
    }

    public Float getSpeedAvg() {
        return speedAvg;
    }

    @Override
    public String toString() {
        return "LapEntity{" +
                "time=" + time +
                ", pilot=" + pilot +
                ", lapNumber=" + lapNumber +
                ", lapTime=" + lapTime +
                ", speedAvg=" + speedAvg +
                '}';
    }
}
