package br.com.helpdev.race.infrastructure.logfile.entities;

import java.time.LocalTime;


public class LapEntity {

    private LocalTime time;
    private PilotEntity pilot;
    private Integer lapNumber;
    private LocalTime lapTime;
    private Float speedAvg;

    public LapEntity(LocalTime time, PilotEntity pilot, Integer lapNumber, LocalTime lapTime, Float speedAvg) {
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
