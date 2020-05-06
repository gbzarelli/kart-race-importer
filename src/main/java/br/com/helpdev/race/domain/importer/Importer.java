package br.com.helpdev.race.domain.importer;

import java.time.LocalDate;

public class Importer {

  private final RaceRepository repository;

    public Importer(final RaceRepository repository) {
      this.repository = repository;
    }

    public By getRaces() {
        return repository::importRacesByDate;
    }

    @FunctionalInterface
    public interface By {
        Races byDate(final LocalDate localDate);
    }
}
