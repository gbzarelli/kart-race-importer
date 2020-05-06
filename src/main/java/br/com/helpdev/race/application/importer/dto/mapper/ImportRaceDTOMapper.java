package br.com.helpdev.race.application.importer.dto.mapper;

import br.com.helpdev.race.application.importer.dto.RacesImported;
import br.com.helpdev.race.application.importer.dto.race.*;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.domain.race.*;
import br.com.helpdev.race.application.importer.dto.AbstractResponseDTO;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportRaceDTOMapper {

    private ImportRaceDTOMapper() {
    }

    public static RacesImported racesToRacesImported(final Races races) {
        final var status = getStatus(races);
        final var racesImported = new RacesImported(status, races.getLocalDate().toString(), racesToRacesDTO(races.getRaces()));
        racesImported.addNotifiable(racesImported);
        return racesImported;
    }

    private static AbstractResponseDTO.Status getStatus(final Races races) {
        var status = AbstractResponseDTO.Status.SUCCESS;
        if (!races.isValid() && races.getRaces().isEmpty()) {
            status = AbstractResponseDTO.Status.ERROR;
        }
        return status;
    }

    private static List<RaceDTO> racesToRacesDTO(final List<Race> races) {
        return races.parallelStream().map(ImportRaceDTOMapper::raceToRaceDTO)
                .collect(Collectors.toList());
    }

    private static RaceDTO raceToRaceDTO(final Race race) {
        final var raceDTO = new RaceDTO();
        raceDTO.setName(race.getRaceName());
        raceDTO.setRaceId(race.getRaceId().toString());
        raceDTO.setNumbersOfLaps(race.getNumbersOfLaps());
        raceDTO.setRaceDate(race.getDate().toString());
        raceDTO.setClassifications(fillPilotFinishClassificationDTO(race));
        raceDTO.setLapsRace(fillLapsRace(race));
        return raceDTO;
    }

    private static Map<Integer, LapRaceDTO> fillLapsRace(final Race race) {
        final var map = new LinkedHashMap<Integer, LapRaceDTO>();
        race.getLaps().forEach((key, lapRace) -> map.put(key, lapRaceToLapRaceDTO(lapRace)));
        return map;
    }

    private static LapRaceDTO lapRaceToLapRaceDTO(final LapRace lapRace) {
        final var lapRaceDTO = new LapRaceDTO();
        lapRaceDTO.setLapNumber(lapRace.getLapNumber());
        lapRaceDTO.setFaster(getFasterPilotDTO(lapRace));
        lapRaceDTO.setLapClassification(fillLapClassificationDTO(lapRace));
        return lapRaceDTO;
    }


    private static FasterPilotDTO getFasterPilotDTO(final LapRace lapRace) {
        final var dto = new FasterPilotDTO();
        final var lapDTO = new LapDTO();
        final var faster = lapRace.getFaster();
        lapDTO.setNumberOfLap(lapRace.getLapNumber());
        lapDTO.setTime(faster.getPilotTime().getFormattedTime());
        lapDTO.setAvgSpeed(faster.getLapInfos().getAvgSpeed());
        dto.setPilot(pilotToPilotDTO(faster.getPilot()));
        dto.setLap(lapDTO);
        return dto;
    }

    private static PilotDTO pilotToPilotDTO(final Pilot pilot) {
        final var pilotDTO = new PilotDTO();
        pilotDTO.setName(pilot.getName());
        pilotDTO.setNumber(pilot.getPilotId().getNumber());
        return pilotDTO;
    }

    private static Map<Integer, PilotClassificationDTO> fillLapClassificationDTO(final LapRace lapRace) {
        final var map = new LinkedHashMap<Integer, PilotClassificationDTO>();
        lapRace.getClassification().forEach((key, classification) ->
                map.put(key, classificationToPilotClassificationDTO(lapRace.getLapNumber(), classification)));
        return map;
    }

    private static PilotClassificationDTO classificationToPilotClassificationDTO(final int lapNumber, final Classification classification) {
        final var dto = new PilotClassificationDTO();
        dto.setLap(lapInfoToLapDTO(lapNumber, classification.getLapInfos()));
        dto.setPilot(pilotToPilotDTO(classification.getPilot()));
        dto.setTimeTo(fillTimeToDTO(classification.getDiffTimes()));
        return dto;
    }

    private static Map<Integer, TimeToDTO> fillTimeToDTO(Map<Integer, PilotTime> diffTimes) {
        final var dto = new LinkedHashMap<Integer, TimeToDTO>();
        diffTimes.forEach((key, pilotTime) ->
                dto.put(key, pilotTimeToTimeToDTO(pilotTime)));
        return dto;
    }

    private static TimeToDTO pilotTimeToTimeToDTO(PilotTime pilotTime) {
        final var dto = new TimeToDTO();
        dto.setPilot(pilotToPilotDTO(pilotTime.getPilot()));
        dto.setDiffTime(pilotTime.getFormattedTime());
        dto.setPlaceInLap(pilotTime.getPlaceInRace());
        return dto;
    }

    private static LapDTO lapInfoToLapDTO(int lapNumber, LapInfos lapInfos) {
        final var dto = new LapDTO();
        dto.setTime(lapInfos.getTime().toString());
        dto.setLapTime(lapInfos.getLapTime().toString());
        dto.setAvgSpeed(lapInfos.getAvgSpeed());
        dto.setNumberOfLap(lapNumber);
        return dto;
    }

    private static Map<Integer, PilotFinishDTO> fillPilotFinishClassificationDTO(Race race) {
        final var map = new LinkedHashMap<Integer, PilotFinishDTO>();
        race.getLastLap().getClassification().forEach((pos, classification) -> {
            map.put(pos, classificationToPilotFinishDTO(race, classification));
        });
        return map;
    }

    private static PilotFinishDTO classificationToPilotFinishDTO(Race race, Classification classification) {
        final var dto = new PilotFinishDTO();
        final var pilotRace = race.getPilotRace(classification.getPilot().getPilotId());
        dto.setAvgSpeed(pilotRace.getAvgSpeed());
        dto.setBestLap(lapInfoToLapDTO(pilotRace.getBestLapNumber(), pilotRace.getBestLap()));
        dto.setPilot(pilotToPilotDTO(pilotRace));

        final var winner = race.getLastLap().getClassification().get(1).getLapInfos();
        final var lapInfos = classification.getLapInfos();
        dto.setTimeToFirst(
                LocalTime.ofNanoOfDay(lapInfos.getTime().toNanoOfDay() - winner.getTime().toNanoOfDay()).toString()
        );
        return dto;
    }


}
