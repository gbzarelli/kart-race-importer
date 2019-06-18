package br.com.helpdev.race.domain.race;

import java.util.concurrent.TimeUnit;

public class PilotTime {

    private Integer placeInRace;
    private Pilot pilot;
    private long timeInNano;

    PilotTime(Integer placeInRace, Pilot pilot, long timeInNano) {
        this.placeInRace = placeInRace;
        this.pilot = pilot;
        this.timeInNano = timeInNano;
    }

    public Integer getPlaceInRace() {
        return placeInRace;
    }

    public Pilot getPilot() {
        return pilot;
    }


    public long getTimeInNano() {
        return timeInNano;
    }

    public long getTimeInMillis() {
        return TimeUnit.NANOSECONDS.toMillis(timeInNano);
    }

    public long getTimeInSeconds() {
        return TimeUnit.NANOSECONDS.toSeconds(timeInNano);
    }

    @Override
    public String toString() {
        return "PilotTime{" +
                " timeInMillis=" + getTimeInMillis() +
                '}';
    }
}
