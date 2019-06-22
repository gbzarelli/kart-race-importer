package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.importer.Races;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class RaceLogFileRepositoryTest {

    private final RaceLogFileRepository repository = new RaceLogFileRepository();

    @Test
    void should_import_zero_races_by_date() {
        LocalDate date = LocalDate.of(2018, Month.APRIL, 1);
        assertImportRaces(0, date);
    }

    @Test
    void should_import_two_races_by_date() {
        LocalDate date = LocalDate.of(2019, Month.JUNE, 18);
        assertImportRaces(2, date);
    }

    @Test
    void should_import_one_races_by_date() {
        LocalDate date = LocalDate.of(2019, Month.JUNE, 1);
        assertImportRaces(1, date);
    }

    private void assertImportRaces(int racesExpected, LocalDate date) {
        Races races = repository.importRacesByDate(date);
        Assertions.assertEquals(racesExpected, races.getRaces().size());
        Assertions.assertEquals(date, races.getLocalDate());
    }
}