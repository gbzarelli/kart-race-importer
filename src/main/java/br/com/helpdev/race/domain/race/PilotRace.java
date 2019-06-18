package br.com.helpdev.race.domain.race;

import java.util.ArrayList;
import java.util.List;

public class PilotRace extends Pilot {

    private Race race;
    private List<LapInfos> laps;
    private LapInfos bestLap;

    public PilotRace(Race race, int number, String name) {
        super(number, name);
        this.race = race;
        this.laps = new ArrayList<>();
        subscribe().toRace(race);
    }

    private ToRace subscribe() {
        return race -> race.addPilot(this);
    }

    public void newLap(LapInfos lap) {
        laps.add(lap);
        verifyPilotBestLap(lap);
        race.updateLapRace(laps.size(), this, lap);
    }

    private void verifyPilotBestLap(LapInfos lap) {
        if (bestLap == null || lap.getLapTime().getNano() < bestLap.getLapTime().getNano()) {
            bestLap = lap;
        }
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
