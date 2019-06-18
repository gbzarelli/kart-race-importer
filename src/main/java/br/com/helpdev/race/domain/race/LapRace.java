package br.com.helpdev.race.domain.race;

import java.time.Instant;
import java.util.*;

public class LapRace {

    private int lapNumber;
    private List<Classification> classification;
    private PilotTime faster;

    LapRace(int lapNumber) {
        this.lapNumber = lapNumber;
        this.classification = new ArrayList<>();
    }

    void updateClassification(PilotRace pilot, LapInfos lap) {
        int placeInRace = getNewPlaceInClassification();
        classification.add(Classification.build(placeInRace, pilot, lap, getClassification()));
        verifyFaster(placeInRace, pilot, lap);
    }

    private void verifyFaster(int placeInRace, Pilot pilot, LapInfos lap) {
        long nano =  lap.getLapTime().toNanoOfDay();
        if (faster == null || nano > faster.getTimeInMillis()) {
            faster = new PilotTime(placeInRace, pilot, nano);
        }
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
