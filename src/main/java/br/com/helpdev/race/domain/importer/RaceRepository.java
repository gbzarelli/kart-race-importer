package br.com.helpdev.race.domain.importer;

import java.time.LocalDate;

public interface RaceRepository {
    Races importRaceByDate(LocalDate localDate);
}
