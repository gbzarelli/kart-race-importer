package br.com.helpdev.race.domain.race;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PilotRace extends Pilot {

    private final RaceId race;
    private final Map<Integer, LapInfos> laps;
    private Integer bestLapNumber;

    PilotRace(final RaceId race, final Pilot pilot) {
        super(pilot.getPilotId(), pilot.getName());
        this.race = race;
        this.laps = new LinkedHashMap<>();
    }

    public ToRace newLap(final LapInfos lap) {
        return race -> {
            final var lapNumber = laps.size() + 1;
            laps.put(lapNumber, lap);
            setBestLap(lapNumber, lap);
            race.updateLapRace(laps.size(), this, lap);
        };
    }

    private void setBestLap(final int lapNumber, final LapInfos lap) {
        if (lap.bestOf(laps.get(bestLapNumber))) bestLapNumber = lapNumber;
    }

    public LapInfos getBestLap() {
        if (laps.isEmpty()) return null;
        return laps.get(bestLapNumber);
    }

    public Integer getBestLapNumber() {
        return bestLapNumber;
    }

    public int getNumbersOfLaps() {
        return laps.size();
    }

    public Float getAvgSpeed() {
        var x = 0.0f;
        for (LapInfos lapInfo : laps.values()) {
            x += lapInfo.getAvgSpeed();
        }
        return x / laps.size();
    }

    public RaceId getRace() {
        return race;
    }

    @FunctionalInterface
    public interface ToRace {
        void toRace(final Race race);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PilotRace pilotRace = (PilotRace) o;
        return Objects.equals(race, pilotRace.race) &&
                Objects.equals(laps, pilotRace.laps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), race, laps);
    }

    @Override
    public String toString() {
        return "PilotRace{" +
                super.toString() +
                '}';
    }
}
