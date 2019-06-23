package br.com.helpdev.race.application.importer.dto.race;

import java.util.Map;

public class PilotClassificationDTO {

    private PilotDTO pilot;
    private LapDTO lap;
    private Map<Integer,TimeToDTO> timeTo;

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

    public Map<Integer, TimeToDTO> getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Map<Integer, TimeToDTO> timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "PilotClassificationDTO{" +
                ", pilot=" + pilot +
                ", lap=" + lap +
                ", timeTo=" + timeTo +
                '}';
    }
}
