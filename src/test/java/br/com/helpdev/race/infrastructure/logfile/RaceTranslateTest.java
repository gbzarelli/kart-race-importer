package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.race.LapInfos;
import br.com.helpdev.race.domain.race.PilotRace;
import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class RaceTranslateTest {

    @Test
    void getLapInfoFromLapEntity() {
        LapEntity lapEntity = new LapEntity(
                LocalTime.of(0, 1, 2),
                new PilotEntity(1, "name1"),
                1,
                LocalTime.of(0, 0, 10),
                10.5f);
        LapInfos lapInfos = RaceTranslate.getLapInfoFromLapEntity(lapEntity);
        assertEquals(lapInfos.getLapTime(), lapEntity.getLapTime());
        assertEquals(lapInfos.getTime(), lapEntity.getTime());
        assertEquals(lapInfos.getAvgSpeed(), lapEntity.getSpeedAvg());
    }

    @Test
    void getPilotRaceFromLapEntity() {
        Race race = new Race("MyRace", LocalDate.of(2019, Month.APRIL, 1));
        PilotEntity pilotEntity = new PilotEntity(1, "name1");
        PilotRace pilotRace = RaceTranslate.getPilotRaceFromPilotEntity(race, pilotEntity);
        assertEquals(pilotRace.getPilotId().getNumber(), pilotEntity.getNumber());
        assertEquals(pilotRace.getName(), pilotEntity.getName());

    }
}