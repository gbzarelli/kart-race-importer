package br.com.helpdev.race.domain.race;

import br.com.helpdev.race.commons.time.TimeUtils;

public class PilotTime {

    private final Integer placeInRace;
    private final Pilot pilot;
    private final long timeInNano;
    private final String formattedTime;

    PilotTime(final Integer placeInRace,
              final Pilot pilot,
              final long timeInNano) {
        this.placeInRace = placeInRace;
        this.pilot = pilot;
        this.timeInNano = timeInNano;
        this.formattedTime = TimeUtils.formatNanoTime(timeInNano);
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

    public String getFormattedTime() {
        return formattedTime;
    }

    boolean isFasterThan(final PilotTime lastFaster) {
        return timeInNano < lastFaster.getTimeInNano();
    }

    @Override
    public String toString() {
        return "PilotTime{" +
                " pilot=" + getPilot().getPilotId().getNumber() +
                " time=" + getFormattedTime() +
                '}';
    }


}
