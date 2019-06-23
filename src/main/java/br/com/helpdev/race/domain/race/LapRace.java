package br.com.helpdev.race.domain.race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LapRace {

    private int lapNumber;
    private List<Classification> classification;
    private PilotTime faster;

    LapRace(int lapNumber) {
        this.lapNumber = lapNumber;
        this.classification = new ArrayList<>();
    }

    void updateClassification(Pilot pilot, LapInfos lap) {
        int placeInRace = getNewPlaceInClassification();
        classification.add(Classification.Builder.create(placeInRace, pilot, lap, getClassification()));
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

    public List<Classification> getClassification() {
        return Collections.unmodifiableList(classification);
    }

    private int getNewPlaceInClassification() {
        return classification.size() + 1;
    }

}
