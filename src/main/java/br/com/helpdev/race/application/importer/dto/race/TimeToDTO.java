package br.com.helpdev.race.application.importer.dto.race;

public class TimeToDTO {
    private int placeInLap;
    private PilotDTO pilot;
    private String diffTime;

    public int getPlaceInLap() {
        return placeInLap;
    }

    public void setPlaceInLap(int placeInLap) {
        this.placeInLap = placeInLap;
    }

    public PilotDTO getPilot() {
        return pilot;
    }

    public void setPilot(PilotDTO pilot) {
        this.pilot = pilot;
    }

    public String getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(String diffTime) {
        this.diffTime = diffTime;
    }

    @Override
    public String toString() {
        return "TimeToDTO{" +
                "placeInLap=" + placeInLap +
                ", pilot=" + pilot +
                ", diffTime='" + diffTime + '\'' +
                '}';
    }
}
