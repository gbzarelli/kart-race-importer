package br.com.helpdev.race.domain.race;

import java.util.ArrayList;
import java.util.List;

import static br.com.helpdev.race.shared.utils.TimeUtils.getDiffInNano;

public class Classification {

    static class Builder {
        static Classification create(int placeInRace, Pilot pilot, LapInfos lap, List<Classification> listClassification) {
            Classification classification = new Classification(placeInRace, pilot, lap);
            if (listClassification.isEmpty()) return classification;

            for (Classification cls : listClassification) {
                PilotTime pilotTime = new PilotTime(cls.placeInRace, cls.pilot, getDiffInNano(lap.getLapTime(), cls.lap.getLapTime()));
                classification.timeTo.add(pilotTime);
            }
            return classification;
        }
    }

    private int placeInRace;
    private Pilot pilot;
    private LapInfos lap;
    private List<PilotTime> timeTo;

    private Classification(int placeInRace, Pilot pilot, LapInfos lap) {
        this.placeInRace = placeInRace;
        this.pilot = pilot;
        this.lap = lap;
        this.timeTo = new ArrayList<>();
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
