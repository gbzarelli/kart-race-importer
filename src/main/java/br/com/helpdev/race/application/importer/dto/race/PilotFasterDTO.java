package br.com.helpdev.race.application.importer.dto.race;

public class PilotFasterDTO {
    private PilotDTO pilot;
    private LapDTO lap;

    public PilotDTO getPilot() {
        return pilot;
    }

    public void setPilot(PilotDTO pilot) {
        this.pilot = pilot;
    }

    public LapDTO getLap() {
        return lap;
    }

    public void setLap(LapDTO lap) {
        this.lap = lap;
    }

    @Override
    public String toString() {
        return "PilotFasterDTO{" +
                "pilot=" + pilot +
                ", lap=" + lap +
                '}';
    }
}
