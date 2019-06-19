package br.com.helpdev.race.domain.race;

import java.util.ArrayList;
import java.util.List;

public class PilotRace extends Pilot {

    private RaceId race;
    private List<LapInfos> laps;
    private LapInfos bestLap;

    PilotRace(RaceId race, Pilot pilot) {
        super(pilot.getPilotId(), pilot.getName());
        this.race = race;
        this.laps = new ArrayList<>();
    }

    public ToRace newLap(LapInfos lap) {
        return race -> {
            laps.add(lap);
            verifyBestLap(lap);
            race.updateLapRace(laps.size(), getPilotId(), lap);
        };
    }

    private void verifyBestLap(LapInfos lap) {
        if (lap.bestOf(bestLap)) bestLap = lap;
    }

    public LapInfos getBestLap() {
        return bestLap;
    }

    public int getNumbersOfLaps() {
        return laps.size();
    }

    public Float getAvgSpeed() {
        Float x = 0.0f;
        for (LapInfos lapInfo : laps) {
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
