package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.race.*;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;

class RaceTranslate {

    static LapInfos getLapInfoFromLapEntity(LapEntity translate) {
        return new LapInfos(translate.getTime(), translate.getLapTime(), translate.getSpeedAvg());
    }

    static PilotRace getPilotRaceFromPilotEntity(Race race, PilotEntity pilotEntity) {
        return new Pilot(new PilotId(pilotEntity.getNumber()), pilotEntity.getName())
                .subscribe()
                .toRace(race);
    }

}
