package br.com.helpdev.race.domain.race;

import java.time.LocalDate;
import java.util.*;

public class Race {

    private final RaceId id;
    private final String name;
    private final Map<PilotId, PilotRace> pilots;
    private final Map<Integer, LapRace> laps;
    private final LocalDate date;

    public Race(final String name, final LocalDate date) {
        this(RaceId.getNewRaceID(), name, date);
    }

    public Race(final RaceId raceId, final String name, final LocalDate date) {
        this.id = raceId;
        this.name = name;
        this.date = date;
        this.pilots = new HashMap<>();
        this.laps = new LinkedHashMap<>();
    }

    public RaceId getRaceId() {
        return id;
    }

    void updateLapRace(final Integer lapNumber, final Pilot pilot, final LapInfos lap) {
        final var lapRace = laps.getOrDefault(lapNumber, new LapRace(lapNumber));
        lapRace.updateClassification(pilot, lap);
        laps.put(lapNumber, lapRace);
    }

    void addPilot(final PilotRace pilot) {
        if (!pilots.containsKey(pilot.getPilotId())) pilots.put(pilot.getPilotId(), pilot);
    }

    public Map<Integer, LapRace> getLaps() {
        return Collections.unmodifiableMap(laps);
    }

    public LapRace getLap(final int lapNumber) {
        if (lapNumber > laps.size()) return null;
        return laps.get(lapNumber);
    }

    public LapRace getLastLap() {
        return laps.get(laps.size());
    }

    public int getNumbersOfLaps() {
        return laps.size();
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
