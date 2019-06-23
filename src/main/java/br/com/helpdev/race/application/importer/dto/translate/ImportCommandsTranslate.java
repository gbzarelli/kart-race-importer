package br.com.helpdev.race.application.importer.dto.translate;

import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.shared.dto.OutputDTO;

public class ImportCommandsTranslate {

    public static RacesImported racesToCommandResult(Races races) {
        OutputDTO.Status status = getStatus(races);
        RacesImported racesImported = new RacesImported(status, races.getLocalDate(), races.getRaces());
        racesImported.addNotifiable(racesImported);
        return racesImported;
    }

    private static OutputDTO.Status getStatus(Races races) {
        OutputDTO.Status status = OutputDTO.Status.SUCCESS;
        if (!races.isValid() && races.getRaces().isEmpty()) {
            status = OutputDTO.Status.ERROR;
        }
        return status;
    }

}
