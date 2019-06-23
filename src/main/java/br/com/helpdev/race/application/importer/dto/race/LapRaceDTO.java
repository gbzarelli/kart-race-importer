package br.com.helpdev.race.application.importer.dto.race;

import java.util.Map;

public class LapRaceDTO {
    private int lapNumber;
    private Map<Integer, PilotClassificationDTO> lapClassification;
    private PilotFasterDTO faster;

    public int getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(int lapNumber) {
        this.lapNumber = lapNumber;
    }

    public Map<Integer, PilotClassificationDTO> getLapClassification() {
        return lapClassification;
    }

    public void setLapClassification(Map<Integer, PilotClassificationDTO> lapClassification) {
        this.lapClassification = lapClassification;
    }

    public PilotFasterDTO getFaster() {
        return faster;
    }

    public void setFaster(PilotFasterDTO faster) {
        this.faster = faster;
    }

    @Override
    public String toString() {
        return "LapRaceDTO{" +
                "lapNumber=" + lapNumber +
                ", lapClassification=" + lapClassification +
                ", faster=" + faster +
                '}';
    }
}
