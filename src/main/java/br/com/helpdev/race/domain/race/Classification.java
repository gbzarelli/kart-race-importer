package br.com.helpdev.race.domain.race;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Classification {

    static Classification build(int placeInRace, PilotRace pilot, LapInfos lap, List<Classification> listClassification) {
        Classification classification = new Classification(placeInRace, pilot, lap);
        if (!listClassification.isEmpty()) {
            for (Classification cls : listClassification) {
                PilotTime pilotTime = new PilotTime(cls.placeInRace, cls.pilot, getDiff(cls.lap.getLapTime(), lap.getLapTime()));
                classification.timeTo.add(pilotTime);
            }
        }
        return classification;
    }

    private static long getDiff(LocalTime localTime_1, LocalTime localTime_2) {
        return localTime_2.toNanoOfDay() - localTime_1.toNanoOfDay();
    }

    private int placeInRace;
    private PilotRace pilot;
    private LapInfos lap;
    private List<PilotTime> timeTo;

    private Classification(int placeInRace, PilotRace pilot, LapInfos lap) {
        this.placeInRace = placeInRace;
        this.pilot = pilot;
        this.lap = lap;
        this.timeTo = new ArrayList<>();
    }

    public PilotRace getPilot() {
        return pilot;
    }

    public int getPlaceInRace() {
        return placeInRace;
    }

    public LapInfos getLapInfos() {
        return lap;
    }

    public PilotTime getDiffTimeTo(int position) {
        if (timeTo.size() < position) return null;
        return timeTo.get(position - 1);
    }

    @Override
    public String toString() {
        return "Classification{" +
                "placeInRace=" + placeInRace +
                ", pilot=" + pilot +
                ", lap=" + lap +
                ", timeTo=" + timeTo +
                '}';
    }

}
