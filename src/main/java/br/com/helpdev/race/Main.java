package br.com.helpdev.race;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.ImporterService;
import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;
import br.com.helpdev.race.application.importer.dto.RacesImported;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.showRacesFrom(LocalDate.of(2019, Month.JUNE, 18));
    }

    private final ImporterFacade importer = new ImporterService();

    private void showRacesFrom(LocalDate localDate) {
        RacesImported races = importer.importRaces(new ImportRaceByDate(localDate));
        System.out.println(new Gson().toJson(races));
        System.out.println(races);
    }

}
