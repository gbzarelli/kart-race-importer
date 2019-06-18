package br.com.helpdev.race.application.impl;

import br.com.helpdev.race.application.RaceFacade;
import br.com.helpdev.race.domain.importer.Importer;
import br.com.helpdev.race.domain.importer.Races;

import java.time.LocalDate;

public class RaceService implements RaceFacade {

    @Override
    public Races getRaces(LocalDate raceDate) {
        return new Importer().getRaces().byDate(raceDate);
    }
}
