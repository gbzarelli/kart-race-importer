package br.com.helpdev.race.infrastructure.logfile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RaceLogFileRepositoryTest {

    private final RaceLogFileRepository repository = new RaceLogFileRepository();

    @Test
    @DisplayName("Should import zero races by date")
    void shouldImportZeroRacesByDate() {
        final var date = LocalDate.of(2018, Month.APRIL, 1);
        assertImportRaces(0, date);
    }

    @Test
    @DisplayName("Should import two races by date")
    void shouldImportTwoRacesByDate() {
        final var date = LocalDate.of(2019, Month.JUNE, 18);
        assertImportRaces(2, date);
    }

    @Test
    @DisplayName("Should import one races by date")
    void shouldImportOneRacesByDate() {
        final var date = LocalDate.of(2019, Month.JUNE, 1);
        assertImportRaces(1, date);
    }

    @Test
    @DisplayName("Should failed when input is null")
    void shouldFailedWhenInputIsNull() {
        assertThrows(NullPointerException.class, () -> {
            assertImportRaces(0, null);
        });
    }

    private void assertImportRaces(int racesExpected, LocalDate date) {
        final var races = repository.importRacesByDate(date);
        assertEquals(racesExpected, races.getRaces().size());
        assertEquals(date, races.getLocalDate());
    }
}