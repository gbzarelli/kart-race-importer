package br.com.helpdev.race.domain.importer;

import br.com.helpdev.race.infrastructure.logfile.RaceLogFileRepository;

import java.time.LocalDate;

public class Importer {

    //Can be use DI
    private final RaceRepository repository = new RaceLogFileRepository();

    public By getRaces() {
        return repository::importRacesByDate;
    }

    @FunctionalInterface
    public interface By {
        Races byDate(LocalDate localDate);
    }
}
