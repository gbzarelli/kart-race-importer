package br.com.helpdev.race;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.ImporterServiceImpl;
import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;
import br.com.helpdev.race.application.importer.dto.race.RaceDTO;
import br.com.helpdev.race.domain.importer.Importer;
import br.com.helpdev.race.infrastructure.logfile.RaceLogFileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {

    private final ImporterFacade importer = new ImporterServiceImpl(new Importer(new RaceLogFileRepository()));

    @Test
    @DisplayName("This test is only to show races from 2019/06/18")
    void showRacesFrom() {
        final var localDate = LocalDate.of(2019, Month.JUNE, 18);
        final var races = importer.importRaces(new ImportRaceByDate(localDate)).getRaces();
        System.out.println("========= RACES FROM " + localDate.toString() + " =========");
        races.forEach(this::printRace);
        assertNotNull(races);
    }

    private void printRace(final RaceDTO raceDTO) {
        System.out.println(raceDTO);
    }

}
