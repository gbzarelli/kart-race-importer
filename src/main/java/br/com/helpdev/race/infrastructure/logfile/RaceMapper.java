package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.race.*;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;

class RaceMapper {

    private RaceMapper() {
    }

    static LapInfos getLapInfoFromLapEntity(LapEntity entity) {
        return new LapInfos(entity.getTime(), entity.getLapTime(), entity.getSpeedAvg());
    }

    static PilotRace getPilotRaceFromPilotEntity(Race race, PilotEntity pilotEntity) {
        return new Pilot(new PilotId(pilotEntity.getNumber()), pilotEntity.getName())
                .subscribe()
                .toRace(race);
    }

}
