package br.com.helpdev.race.application;

import br.com.helpdev.race.domain.importer.Races;

import java.time.LocalDate;

public interface RaceFacade {
    Races getRaces(LocalDate now);
}
