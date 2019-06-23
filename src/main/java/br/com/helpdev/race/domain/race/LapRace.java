package br.com.helpdev.race.domain.race;

import java.util.*;

public class LapRace {

    private int lapNumber;
    private Map<Integer, Classification> classification;
    private PilotTime faster;

    LapRace(int lapNumber) {
        this.lapNumber = lapNumber;
        this.classification = new LinkedHashMap<>();
    }

    void updateClassification(Pilot pilot, LapInfos lap) {
        int placeInRace = getNewPlaceInClassification();
        classification.put(classification.size() + 1, Classification.Builder.create(placeInRace, pilot, lap, getClassification()));
        verifyFaster(pilot, lap, placeInRace);
    }

    private void verifyFaster(Pilot pilot, LapInfos lap, int placeInRace) {
        PilotTime pilotTime = new PilotTime(placeInRace, pilot, lap.getLapTime().toNanoOfDay());
        faster = pilotTime.getFasterThan(faster);
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public PilotTime getFaster() {
        return faster;
    }

    public Map<Integer,Classification> getClassification() {
        return Collections.unmodifiableMap(classification);
    }

    private int getNewPlaceInClassification() {
        return classification.size() + 1;
    }

}
