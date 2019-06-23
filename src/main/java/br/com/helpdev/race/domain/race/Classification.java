package br.com.helpdev.race.domain.race;

import java.util.*;

import static br.com.helpdev.race.shared.utils.TimeUtils.getDiffInNano;

public class Classification {

    static class Builder {
        static Classification create(int placeInRace, Pilot pilot, LapInfos lap, Map<Integer,Classification> listClassification) {
            Classification classification = new Classification(placeInRace, pilot, lap);
            if (listClassification.isEmpty()) return classification;

            listClassification.forEach((position,cls)->{
                PilotTime pilotTime = new PilotTime(cls.placeInRace, cls.pilot, getDiffInNano(lap.getLapTime(), cls.lap.getLapTime()));
                classification.timeTo.put(position,pilotTime);
            });

            return classification;
        }
    }

    private int placeInRace;
    private Pilot pilot;
    private LapInfos lap;
    private Map<Integer,PilotTime> timeTo;

    private Classification(int placeInRace, Pilot pilot, LapInfos lap) {
        this.placeInRace = placeInRace;
        this.pilot = pilot;
        this.lap = lap;
        this.timeTo = new LinkedHashMap<>();
    }

    public Pilot getPilot() {
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
        return timeTo.get(position);
    }

    public Map<Integer, PilotTime> getDiffTimes() {
        return Collections.unmodifiableMap(timeTo);
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
