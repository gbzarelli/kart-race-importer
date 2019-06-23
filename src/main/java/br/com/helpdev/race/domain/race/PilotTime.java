package br.com.helpdev.race.domain.race;

import br.com.helpdev.race.shared.utils.TimeUtils;

public class PilotTime {

    private Integer placeInRace;
    private Pilot pilot;
    private long timeInNano;
    private String formattedTime;

    PilotTime(Integer placeInRace, Pilot pilot, long timeInNano) {
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

    boolean isFasterThan(PilotTime lastFaster) {
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
