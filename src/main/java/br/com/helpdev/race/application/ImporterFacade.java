package br.com.helpdev.race.application;

import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;
import br.com.helpdev.race.application.importer.dto.RacesImported;

public interface ImporterFacade {
    RacesImported importRaces(ImportRaceByDate command);
}
