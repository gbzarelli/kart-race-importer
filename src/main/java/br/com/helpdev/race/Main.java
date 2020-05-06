package br.com.helpdev.race;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.ImporterServiceImpl;
import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;
import br.com.helpdev.race.application.importer.dto.RacesImported;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(final String[] args) {
        final var main = new Main();
        main.showRacesFrom(LocalDate.of(2019, Month.JUNE, 1));
    }

    private final ImporterFacade importer = new ImporterServiceImpl();

    private void showRacesFrom(final LocalDate localDate) {
        RacesImported races = importer.importRaces(new ImportRaceByDate(localDate));
        System.out.println(new Gson().toJson(races));
    }
}
