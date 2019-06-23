package br.com.helpdev.race.application.importer.dto.race;

public class TimeToDTO {
    private int placeInLap;
    private PilotDTO pilotDTO;
    private String diffTime;

    public int getPlaceInLap() {
        return placeInLap;
    }

    public void setPlaceInLap(int placeInLap) {
        this.placeInLap = placeInLap;
    }

    public PilotDTO getPilotDTO() {
        return pilotDTO;
    }

    public void setPilotDTO(PilotDTO pilotDTO) {
        this.pilotDTO = pilotDTO;
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
                ", pilotDTO=" + pilotDTO +
                ", diffTime='" + diffTime + '\'' +
                '}';
    }
}
