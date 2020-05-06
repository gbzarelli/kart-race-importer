package br.com.helpdev.race;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.Month;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.ImporterServiceImpl;
import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;
import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.domain.importer.Importer;
import br.com.helpdev.race.infrastructure.logfile.RaceLogFileRepository;

public class Main {

    public static void main(final String[] args) {
        final var main = new Main();
        main.showRacesFrom(LocalDate.of(2019, Month.JUNE, 1));
    }

    private final ImporterFacade facade;

    public Main() {
      final var repository = new RaceLogFileRepository();
      final var importer = new Importer(repository);
      facade = new ImporterServiceImpl(importer);
    }

    private void showRacesFrom(final LocalDate localDate) {
        RacesImported races = facade.importRaces(new ImportRaceByDate(localDate));
        System.out.println(new Gson().toJson(races));
    }
}
