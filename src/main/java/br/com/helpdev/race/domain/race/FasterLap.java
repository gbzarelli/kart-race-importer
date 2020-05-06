package br.com.helpdev.race.domain.race;

public class FasterLap {

    private final Pilot pilot;
    private final PilotTime pilotTime;
    private final LapInfos lapInfos;

    FasterLap(final Pilot pilot,
              final PilotTime pilotTime,
              final LapInfos lapInfos) {
        this.pilot = pilot;
        this.pilotTime = pilotTime;
        this.lapInfos = lapInfos;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public PilotTime getPilotTime() {
        return pilotTime;
    }

    public LapInfos getLapInfos() {
        return lapInfos;
    }
}
