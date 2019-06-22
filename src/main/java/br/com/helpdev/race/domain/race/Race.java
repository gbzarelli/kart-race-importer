package br.com.helpdev.race.domain.race;

import java.time.LocalDate;
import java.util.*;

public class Race {

    private RaceId id;
    private String name;
    private Map<PilotId, PilotRace> pilots;
    private Map<Integer, LapRace> laps;
    private LocalDate date;

    public Race(String name, LocalDate date) {
        this(RaceId.getNewRaceID(), name, date);
    }

    public Race(RaceId raceId, String name, LocalDate date) {
        this.id = raceId;
        this.name = name;
        this.date = date;
        this.pilots = new HashMap<>();
        this.laps = new LinkedHashMap<>();
    }

    public RaceId getRaceId() {
        return id;
    }

    void updateLapRace(Integer lapNumber, PilotId pilot, LapInfos lap) {
        LapRace lapRace = laps.getOrDefault(lapNumber, new LapRace(lapNumber));
        lapRace.updateClassification(pilot, lap);
        laps.put(lapNumber, lapRace);
    }

    void addPilot(PilotRace pilot) {
        if (!pilots.containsKey(pilot.getPilotId())) pilots.put(pilot.getPilotId(), pilot);
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

    public Pilot getWinner() {
        PilotId pilotId = laps.get(laps.size()).getClassification().get(0).getPilot();
        return pilots.get(pilotId);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRaceName() {
        return name;
    }

    public PilotRace getPilotRace(PilotId pilot) {
        return pilots.get(pilot);
    }

    public Collection<PilotRace> getPilots() {
        return Collections.unmodifiableCollection(pilots.values());
    }
}
