package br.com.helpdev.race.application;

import br.com.helpdev.race.application.importer.command.ImportRaceByDateCommand;
import br.com.helpdev.race.application.importer.command.RacesImportedCommandResult;

public interface ImporterFacade {
    RacesImportedCommandResult importRaces(ImportRaceByDateCommand command);
}
