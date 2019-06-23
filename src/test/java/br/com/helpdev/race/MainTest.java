package br.com.helpdev.race;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.ImporterService;
import br.com.helpdev.race.application.importer.dto.ImportRaceByDate;
import br.com.helpdev.race.application.importer.dto.race.RaceDTO;
import br.com.helpdev.race.domain.race.LapRace;
import br.com.helpdev.race.domain.race.PilotRace;
import br.com.helpdev.race.domain.race.PilotTime;
import br.com.helpdev.race.domain.race.Race;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class MainTest {

    private final ImporterFacade importer = new ImporterService();

    @Test
    void showRacesFrom() {
        LocalDate localDate = LocalDate.of(2019, Month.JUNE, 18);
        List<RaceDTO> races = importer.importRaces(new ImportRaceByDate(localDate)).getRaces();
        System.out.println("========= RACES FROM " + localDate.toString() + " =========");
        races.forEach(this::printRace);
    }

    private void printRace(RaceDTO raceDTO) {
        System.out.println(raceDTO);
        //TODO
    }

}
