package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.race.*;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;

class RaceMapper {

    private RaceMapper() {
    }

    public static LapInfos getLapInfoFromLapEntity(final LapEntity entity) {
        return new LapInfos(entity.getTime(), entity.getLapTime(), entity.getSpeedAvg());
    }

    public static PilotRace getPilotRaceFromPilotEntity(final Race race,
                                                        final PilotEntity pilotEntity) {
        return new Pilot(new PilotId(pilotEntity.getNumber()), pilotEntity.getName())
                .subscribe()
                .toRace(race);
    }

}
