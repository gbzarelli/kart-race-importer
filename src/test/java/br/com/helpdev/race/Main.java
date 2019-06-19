package br.com.helpdev.race;

import br.com.helpdev.race.application.ImporterFacade;
import br.com.helpdev.race.application.importer.ImporterService;
import br.com.helpdev.race.application.importer.command.ImportRaceByDateCommand;
import br.com.helpdev.race.domain.race.LapRace;
import br.com.helpdev.race.domain.race.PilotRace;
import br.com.helpdev.race.domain.race.PilotTime;
import br.com.helpdev.race.domain.race.Race;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class Main {

    private final ImporterFacade importer = new ImporterService();

    @Test
    void showRacesFrom() {
        LocalDate localDate = LocalDate.of(2019, Month.JUNE, 18);
        List<Race> races = importer.importRaces(new ImportRaceByDateCommand(localDate)).getRaces();
        System.out.println("========= RACES FROM " + localDate.toString() + " =========");
        races.forEach(this::printRace);
    }

    private void printRace(Race race) {
        System.out.println("Race Name: " + race.getRaceName());
        System.out.println("Number of laps: " + race.getNumbersOfLaps());
        System.out.print("Winner: ");
        printWinner(race.getLastLap());

        System.out.println("\nPilots:");
        race.getPilots().forEach(this::printPilot);

        System.out.println("\nLaps: ");
        for (int i = 1; i <= race.getNumbersOfLaps(); i++) {
            LapRace lap = race.getLap(i);
            printLap(lap);
        }
    }

    private void printWinner(LapRace lastLap) {
        System.out.println(lastLap.getClassification().get(0).getPilot().getName());
    }

    private void printLap(LapRace lap) {
        System.out.println("\tLap Number: " + lap.getLapNumber());
        PilotTime faster = lap.getFaster();
        System.out.println("\tFaster in lap: " + faster.getPilot().getName() + " - Time: " + faster.getFormattedTime());
        System.out.println("\tClassification lap: " + lap.getClassification());
    }

    private void printPilot(PilotRace pilotRace) {
        System.out.println("\tPilot: " + pilotRace.getName());
        System.out.println("\tAVG Speed in race: " + pilotRace.getAvgSpeed());
        System.out.println("\tBest Lap: " + pilotRace.getBestLap());
        System.out.println("\tNumber of laps: " + pilotRace.getNumbersOfLaps());
    }

}
