package br.com.helpdev.race.domain.race;

public class FasterLap {

    private Pilot pilot;
    private PilotTime pilotTime;
    private LapInfos lapInfos;

    FasterLap(Pilot pilot, PilotTime pilotTime, LapInfos lapInfos) {
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
