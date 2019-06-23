package br.com.helpdev.race.application.importer.dto.race;

import java.util.Map;

public class RaceDTO {
    private String name;
    private String raceId;
    private String raceDate;
    private int numbersOfLaps;
    private Map<Integer, PilotFinishDTO> classifications;
    private Map<Integer, LapRaceDTO> lapsRace;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    public int getNumbersOfLaps() {
        return numbersOfLaps;
    }

    public void setNumbersOfLaps(int numbersOfLaps) {
        this.numbersOfLaps = numbersOfLaps;
    }

    public Map<Integer, PilotFinishDTO> getClassifications() {
        return classifications;
    }

    public void setClassifications(Map<Integer, PilotFinishDTO> classifications) {
        this.classifications = classifications;
    }

    public Map<Integer, LapRaceDTO> getLapsRace() {
        return lapsRace;
    }

    public void setLapsRace(Map<Integer, LapRaceDTO> lapsRace) {
        this.lapsRace = lapsRace;
    }

    @Override
    public String toString() {
        return "RaceDTO{" +
                "name='" + name + '\'' +
                ", raceId='" + raceId + '\'' +
                ", raceDate='" + raceDate + '\'' +
                ", numbersOfLaps=" + numbersOfLaps +
                ", classifications=" + classifications +
                ", lapsRace=" + lapsRace +
                '}';
    }
}
