package br.com.helpdev.race.application.importer;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.command.RacesImportedCommandResult;
import br.com.helpdev.race.application.importer.command.translate.ImportCommandsTranslate;
import br.com.helpdev.race.domain.importer.Importer;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.application.importer.command.ImportRaceByDateCommand;

public class ImporterService implements ImporterFacade {

    @Override
    public RacesImportedCommandResult importRaces(ImportRaceByDateCommand command) {
        Races races = new Importer().getRaces().byDate(command.getLocalDate());
        return ImportCommandsTranslate.racesToCommandResult(races);
    }
}
