package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceMapperTest {

    @Test
    @DisplayName("Should get lap infos from lap entity with success")
    void shouldGetLapInfosFromLapEntityWithSuccess() {
        final var lapEntity = new LapEntity(
                LocalTime.of(0, 1, 2),
                new PilotEntity(1, "name1"),
                1,
                LocalTime.of(0, 0, 10),
                10.5f);
        final var lapInfos = RaceMapper.getLapInfoFromLapEntity(lapEntity);
        assertEquals(lapInfos.getLapTime(), lapEntity.getLapTime());
        assertEquals(lapInfos.getTime(), lapEntity.getTime());
        assertEquals(lapInfos.getAvgSpeed(), lapEntity.getSpeedAvg());
    }

    @Test
    @DisplayName("Should get pilot race from entity")
    void getPilotRaceFromLapEntity() {
        final var race = new Race("MyRace", LocalDate.of(2019, Month.APRIL, 1));
        final var pilotEntity = new PilotEntity(1, "name1");
        final var pilotRace = RaceMapper.getPilotRaceFromPilotEntity(race, pilotEntity);
        assertEquals(pilotRace.getPilotId().getNumber(), pilotEntity.getNumber());
        assertEquals(pilotRace.getName(), pilotEntity.getName());
    }
}