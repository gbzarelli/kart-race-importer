package br.com.helpdev.race.application.importer.dto.translate;

import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.application.importer.dto.race.*;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.domain.race.LapRace;
import br.com.helpdev.race.domain.race.Pilot;
import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.shared.dto.OutputDTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportRaceDTOTranslate {

    public static RacesImported racesToCommandResult(Races races) {
        OutputDTO.Status status = getStatus(races);
        RacesImported racesImported = new RacesImported(status, races.getLocalDate(), racesToRacesDTO(races.getRaces()));
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

    public static List<RaceDTO> racesToRacesDTO(List<Race> races) {
        return races.parallelStream().map(ImportRaceDTOTranslate::raceToRaceDTO)
                .collect(Collectors.toList());
    }

    public static RaceDTO raceToRaceDTO(Race race) {
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setName(race.getRaceName());
        raceDTO.setRaceId(race.getRaceId().toString());
        raceDTO.setNumbersOfLaps(race.getNumbersOfLaps());
        raceDTO.setRaceDate(race.getDate().toString());
        raceDTO.setClassifications(fillPilotFinishClassificationDTO(race));
        raceDTO.setLapsRace(fillLapsRace(race));
        return raceDTO;
    }

    private static Map<Integer, LapRaceDTO> fillLapsRace(Race race) {
        Map<Integer, LapRaceDTO> map = new LinkedHashMap<>();
        race.getLaps().forEach((key, lapRace) -> map.put(key, lapRaceToLapRaceDTO(lapRace)));
        return map;
    }

    private static LapRaceDTO lapRaceToLapRaceDTO(LapRace lapRace) {
        LapRaceDTO lapRaceDTO = new LapRaceDTO();
        lapRaceDTO.setLapNumber(lapRace.getLapNumber());
        PilotFasterDTO fasterDTO = getPilotFasterDTO(lapRace);
        lapRaceDTO.setFaster(fasterDTO);
        lapRaceDTO.setLapClassification(fillLapClassificationDTO(lapRace));
        return lapRaceDTO;
    }


    private static PilotFasterDTO getPilotFasterDTO(LapRace lapRace) {
        PilotFasterDTO fasterDTO = new PilotFasterDTO();
        LapDTO lapDTO = new LapDTO();
        lapDTO.setNumberOfLap(lapRace.getLapNumber());
        lapDTO.setTime(lapRace.getFaster().getFormattedTime());
        PilotDTO pilotDTO = getPilotDTO(lapRace.getFaster().getPilot());
        fasterDTO.setLap(lapDTO);
        fasterDTO.setPilot(pilotDTO);
        return fasterDTO;
    }

    private static PilotDTO getPilotDTO(Pilot pilot) {
        PilotDTO pilotDTO = new PilotDTO();
        pilotDTO.setName(pilot.getName());
        pilotDTO.setNumber(pilot.getPilotId().getNumber());
        return pilotDTO;
    }

    private static Map<Integer, PilotClassificationDTO> fillLapClassificationDTO(LapRace lapRace) {
        Map<Integer, PilotClassificationDTO> map = new LinkedHashMap<>();
        //TODO
        return map;
    }

    private static Map<Integer, PilotFinishDTO> fillPilotFinishClassificationDTO(Race race) {
        Map<Integer, PilotFinishDTO> map = new LinkedHashMap<>();
        //TODO
        return map;
    }


}
