package br.com.helpdev.race.application.importer.command.translate;

import br.com.helpdev.race.application.importer.command.RacesImportedCommandResult;
import br.com.helpdev.race.domain.importer.Races;

public class ImportCommandsTranslate {

    public static RacesImportedCommandResult racesToCommandResult(Races races) {
        return new RacesImportedCommandResult(races.getLocalDate(), races.getRaces());
    }

}
