package br.com.helpdev.race.application.importer.dto.race;

public class LapDTO {
    private int numberOfLap;
    private String time;
    private float avgSpeed;

    public int getNumberOfLap() {
        return numberOfLap;
    }

    public void setNumberOfLap(int numberOfLap) {
        this.numberOfLap = numberOfLap;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    @Override
    public String toString() {
        return "LapDTO{" +
                "numberOfLap=" + numberOfLap +
                ", time='" + time + '\'' +
                ", avgSpeed=" + avgSpeed +
                '}';
    }
}
