package br.com.helpdev.race.domain.race;

import java.time.LocalDate;
import java.util.*;

public class Race {

    private RaceId id;
    private String name;
    private List<PilotRace> pilots;
    private Map<Integer, LapRace> laps;
    private LocalDate date;

    public Race(String name, LocalDate date) {
        this.id = RaceId.getNewRaceID();
        this.name = name;
        this.date = date;
        this.pilots = new ArrayList<>();
        this.laps = new LinkedHashMap<>();
    }

    public RaceId getId() {
        return id;
    }

    void updateLapRace(Integer lapNumber, PilotRace pilot, LapInfos lap) {
        LapRace lapRace = laps.getOrDefault(lapNumber, new LapRace(lapNumber));
        lapRace.updateClassification(pilot, lap);
        laps.put(lapNumber, lapRace);
    }

    void addPilot(PilotRace pilot) {
        if (!pilots.contains(pilot)) pilots.add(pilot);
    }

    public Map<Integer, LapRace> getLaps() {
        return Collections.unmodifiableMap(laps);
    }

    public LapRace getLap(int lapNumber) {
        if (lapNumber > laps.size()) return null;
        return laps.get(lapNumber);
    }

    public LapRace getLastLap() {
        return laps.get(laps.size());
    }

    public int getNumbersOfLaps() {
        return laps.size();
    }

    public PilotRace getWinner() {
        return laps.get(laps.size()).getClassification().get(0).getPilot();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRaceName() {
        return name;
    }

    public List<PilotRace> getPilots() {
        return Collections.unmodifiableList(pilots);
    }
}
