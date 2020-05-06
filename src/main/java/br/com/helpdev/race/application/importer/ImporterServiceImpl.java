package br.com.helpdev.race.application.importer;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;
import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.application.importer.dto.mapper.ImportRaceDTOMapper;
import br.com.helpdev.race.domain.importer.Importer;

public class ImporterServiceImpl implements ImporterFacade {

    private final Importer importer;

    public ImporterServiceImpl(final Importer importer) {
        this.importer = importer;
    }

    @Override
    public RacesImported importRaces(final ImportRaceByDate command) {
        final var races = importer.getRaces().byDate(command.getLocalDate());
        return ImportRaceDTOMapper.racesToRacesImported(races);
    }
}
