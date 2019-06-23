package br.com.helpdev.race.domain.race;

import java.util.LinkedHashMap;
import java.util.Map;

public class PilotRace extends Pilot {

    private RaceId race;
    private Map<Integer, LapInfos> laps;
    private Integer bestLapNumber;

    PilotRace(RaceId race, Pilot pilot) {
        super(pilot.getPilotId(), pilot.getName());
        this.race = race;
        this.laps = new LinkedHashMap<>();
    }

    public ToRace newLap(LapInfos lap) {
        return race -> {
            int lapNumber = laps.size() + 1;
            laps.put(lapNumber, lap);
            verifyBestLap(lapNumber, lap);
            race.updateLapRace(laps.size(), this, lap);
        };
    }

    private void verifyBestLap(int lapNumber, LapInfos lap) {
        if (lap.bestOf(laps.get(bestLapNumber))) bestLapNumber = lapNumber;
    }

    public LapInfos getBestLap() {
        if (laps.isEmpty()) return null;
        return laps.get(bestLapNumber);
    }

    public int getNumbersOfLaps() {
        return laps.size();
    }

    public Float getAvgSpeed() {
        Float x = 0.0f;
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
        void toRace(Race race);
    }

    @Override
    public String toString() {
        return "PilotRace{" +
                super.toString() +
                '}';
    }
}
