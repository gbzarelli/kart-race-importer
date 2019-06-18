package br.com.helpdev.race.presentation;

import br.com.helpdev.race.application.RaceFacade;
import br.com.helpdev.race.application.impl.RaceService;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.domain.race.*;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.showRacesFrom(LocalDate.of(2019, Month.JUNE, 18));
    }

    private final RaceFacade raceService = new RaceService();

    public void showRacesFrom(LocalDate localDate) {
        Races races = raceService.getRaces(localDate);
        System.out.println("========= RACES FROM " + localDate.toString() + " =========");
        races.getRaces().forEach(this::printRace);
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
        System.out.println("\tFaster in lap: " + faster.getPilot().getName() + " - Time: " + faster.getTimeInSeconds() + "seg");
        System.out.println("\tClassification lap: " + lap.getClassification());
    }

    private void printPilot(PilotRace pilotRace) {
        System.out.println("\tPilot: " + pilotRace.getName());
        System.out.println("\tAVG Speed in race: " + pilotRace.getAvgSpeed());
        System.out.println("\tBest Lap: " + pilotRace.getBestLap());
        System.out.println("\tNumber of laps: " + pilotRace.getNumbersOfLaps());
    }

}
