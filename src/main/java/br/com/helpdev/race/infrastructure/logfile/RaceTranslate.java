package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.race.*;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;

public class RaceTranslate {

    public static LapInfos getLapInfoFromLapEntity(LapEntity translate) {
        return new LapInfos(translate.getTime(), translate.getLapTime(), translate.getSpeedAvg());
    }

    public static PilotRace getPilotRaceFromLapEntity(Race race, LapEntity translate) {
        return new Pilot(new PilotId(translate.getPilot().getNumber()), translate.getPilot().getName()).subscribe().toRace(race);
    }

}
