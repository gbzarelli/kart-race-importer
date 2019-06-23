package br.com.helpdev.race.application.importer.dto.translate;

import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.application.importer.dto.race.*;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.domain.race.*;
import br.com.helpdev.race.shared.dto.OutputDTO;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportRaceDTOTranslate {

    public static RacesImported racesToRacesImported(Races races) {
        OutputDTO.Status status = getStatus(races);
        RacesImported racesImported = new RacesImported(status, races.getLocalDate().toString(), racesToRacesDTO(races.getRaces()));
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
        lapRaceDTO.setFaster(getPilotFasterDTO(lapRace));
        lapRaceDTO.setLapClassification(fillLapClassificationDTO(lapRace));
        return lapRaceDTO;
    }


    private static PilotFasterDTO getPilotFasterDTO(LapRace lapRace) {
        PilotFasterDTO dto = new PilotFasterDTO();
        LapDTO lapDTO = new LapDTO();
        FasterLap faster = lapRace.getFaster();
        lapDTO.setNumberOfLap(lapRace.getLapNumber());
        lapDTO.setTime(faster.getPilotTime().getFormattedTime());
        lapDTO.setAvgSpeed(faster.getLapInfos().getAvgSpeed());
        dto.setPilot(pilotToPilotDTO(faster.getPilot()));
        dto.setLap(lapDTO);
        return dto;
    }

    private static PilotDTO pilotToPilotDTO(Pilot pilot) {
        PilotDTO pilotDTO = new PilotDTO();
        pilotDTO.setName(pilot.getName());
        pilotDTO.setNumber(pilot.getPilotId().getNumber());
        return pilotDTO;
    }

    private static Map<Integer, PilotClassificationDTO> fillLapClassificationDTO(LapRace lapRace) {
        Map<Integer, PilotClassificationDTO> map = new LinkedHashMap<>();
        lapRace.getClassification().forEach((key, classification) ->
                map.put(key, classificationToPilotClassificationDTO(lapRace.getLapNumber(), classification)));
        return map;
    }

    private static PilotClassificationDTO classificationToPilotClassificationDTO(int lapNumber, Classification classification) {
        PilotClassificationDTO dto = new PilotClassificationDTO();
        dto.setLap(lapInfoToLapDTO(lapNumber, classification.getLapInfos()));
        dto.setPilot(pilotToPilotDTO(classification.getPilot()));
        dto.setTimeTo(fillTimeToDTO(classification.getDiffTimes()));
        return dto;
    }

    private static Map<Integer, TimeToDTO> fillTimeToDTO(Map<Integer, PilotTime> diffTimes) {
        Map<Integer, TimeToDTO> dto = new LinkedHashMap<>();
        diffTimes.forEach((key, pilotTime) ->
                dto.put(key, pilotTimeToTimeToDTO(pilotTime)));
        return dto;
    }

    private static TimeToDTO pilotTimeToTimeToDTO(PilotTime pilotTime) {
        TimeToDTO dto = new TimeToDTO();
        dto.setPilot(pilotToPilotDTO(pilotTime.getPilot()));
        dto.setDiffTime(pilotTime.getFormattedTime());
        dto.setPlaceInLap(pilotTime.getPlaceInRace());
        return dto;
    }

    private static LapDTO lapInfoToLapDTO(int lapNumber, LapInfos lapInfos) {
        LapDTO dto = new LapDTO();
        dto.setTime(lapInfos.getTime().toString());
        dto.setLapTime(lapInfos.getLapTime().toString());
        dto.setAvgSpeed(lapInfos.getAvgSpeed());
        dto.setNumberOfLap(lapNumber);
        return dto;
    }

    private static Map<Integer, PilotFinishDTO> fillPilotFinishClassificationDTO(Race race) {
        Map<Integer, PilotFinishDTO> map = new LinkedHashMap<>();
        race.getLastLap().getClassification().forEach((pos, classification) -> {
            map.put(pos, classificationToPilotFinishDTO(race, classification));
        });
        return map;
    }

    private static PilotFinishDTO classificationToPilotFinishDTO(Race race, Classification classification) {
        PilotFinishDTO dto = new PilotFinishDTO();
        PilotRace pilotRace = race.getPilotRace(classification.getPilot().getPilotId());
        dto.setAvgSpeed(pilotRace.getAvgSpeed());
        dto.setBestLap(lapInfoToLapDTO(pilotRace.getBestLapNumber(), pilotRace.getBestLap()));
        dto.setPilot(pilotToPilotDTO(pilotRace));

        LapInfos winner = race.getLastLap().getClassification().get(1).getLapInfos();
        LapInfos lapInfos = classification.getLapInfos();
        dto.setTimeToFirst(
                LocalTime.ofNanoOfDay(lapInfos.getTime().toNanoOfDay()-winner.getTime().toNanoOfDay()).toString()
        );
        return dto;
    }


}
