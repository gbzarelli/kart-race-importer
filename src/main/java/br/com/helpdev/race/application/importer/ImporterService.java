package br.com.helpdev.race.application.importer;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.application.importer.dto.mapper.ImportRaceDTOMapper;
import br.com.helpdev.race.domain.importer.Importer;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;

public class ImporterService implements ImporterFacade {

    @Override
    public RacesImported importRaces(ImportRaceByDate command) {
        Races races = new Importer().getRaces().byDate(command.getLocalDate());
        return ImportRaceDTOMapper.racesToRacesImported(races);
    }
}
