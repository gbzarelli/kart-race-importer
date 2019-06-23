package br.com.helpdev.race.application.importer.dto.race;

public class PilotFinishDTO {
    private PilotDTO pilot;
    private float avgSpeed;
    private LapDTO bestLap;
    private String timeToFirst;

    public String getTimeToFirst() {
        return timeToFirst;
    }

    public void setTimeToFirst(String timeToFirst) {
        this.timeToFirst = timeToFirst;
    }

    public PilotDTO getPilot() {
        return pilot;
    }

    public void setPilot(PilotDTO pilot) {
        this.pilot = pilot;
    }

    public float getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public LapDTO getBestLap() {
        return bestLap;
    }

    public void setBestLap(LapDTO bestLap) {
        this.bestLap = bestLap;
    }

    @Override
    public String toString() {
        return "PilotFinishDTO{" +
                "pilot=" + pilot +
                ", avgSpeed=" + avgSpeed +
                ", bestLap=" + bestLap +
                ", timeToFirst='" + timeToFirst + '\'' +
                '}';
    }
}
