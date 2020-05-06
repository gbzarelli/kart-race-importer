package br.com.helpdev.race.domain.race;


import java.util.*;

public class LapRace {

    private final int lapNumber;
    private final Map<Integer, Classification> classification;
    private FasterLap faster;

    LapRace(final int lapNumber) {
        this.lapNumber = lapNumber;
        this.classification = new LinkedHashMap<>();
    }

    void updateClassification(final Pilot pilot,
                              final LapInfos lap) {
        int placeInRace = getNewPlaceInClassification();
        classification.put(classification.size() + 1, Classification.Builder.create(placeInRace, pilot, lap, getClassification()));
        setFaster(pilot, lap, placeInRace);
    }

    private void setFaster(final Pilot pilot,
                           final LapInfos lap,
                           final int placeInRace) {
        PilotTime pilotTime = new PilotTime(placeInRace, pilot, lap.getLapTime().toNanoOfDay());
        if (faster == null || pilotTime.isFasterThan(faster.getPilotTime())) {
            faster = new FasterLap(pilot, pilotTime, lap);
        }
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public FasterLap getFaster() {
        return faster;
    }

    public Map<Integer, Classification> getClassification() {
        return Collections.unmodifiableMap(classification);
    }

    private int getNewPlaceInClassification() {
        return classification.size() + 1;
    }

}
