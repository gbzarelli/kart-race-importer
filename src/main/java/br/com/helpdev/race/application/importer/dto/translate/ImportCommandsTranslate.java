package br.com.helpdev.race.application.importer.dto.translate;

import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.domain.importer.Races;

public class ImportCommandsTranslate {

    public static RacesImported racesToCommandResult(Races races) {
        return new RacesImported(races.getLocalDate(), races.getRaces());
    }

}
