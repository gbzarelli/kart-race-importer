package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.importer.RaceRepository;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.domain.race.PilotRace;
import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static br.com.helpdev.race.infrastructure.RaceResources.getPathLogRacesFolder;
import static br.com.helpdev.race.infrastructure.logfile.RaceTranslate.getLapInfoFromLapEntity;
import static br.com.helpdev.race.infrastructure.logfile.RaceTranslate.getPilotRaceFromLapEntity;

public class RaceLogFileRepository implements RaceRepository {

    private static final String DATE_TIME_FORMATTER_PATTERN_YYYYMMMDD = "yyyyMMdd";

    @Override
    public Races importRacesByDate(LocalDate localDate) {
        List<Race> races = new ArrayList<>();
        try {
            Stream<Path> pathStream = getFilesByDate(getPathLogRacesFolder(), localDate);
            pathStream.parallel().forEach(path -> {
                try {
                    races.add(getRaceFromPath(path, localDate));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Races.getRaces(races, localDate);
    }

    private Race getRaceFromPath(Path path, LocalDate date) throws IOException {
        BufferedReader bis = getBufferedReader(path);
        bis.readLine();//SKIP FIRST LINE
        Race race = new Race(path.toString(), date);
        fillRace(bis, race);
        return race;
    }

    private void fillRace(BufferedReader bis, Race race) throws IOException {
        Map<Integer, PilotRace> pilots = new HashMap<>();

        while (bis.ready()) {
            String line = bis.readLine();
            LapEntity translate = LapLogFileTranslate.translate(line);
            PilotRace pilot = pilots.get(translate.getPilot().getNumber());
            if (pilot == null) {
                pilot = getPilotRaceFromLapEntity(race, translate);
                pilots.put(pilot.getPilotId().getNumber(), pilot);
            }
            pilot.newLap(getLapInfoFromLapEntity(translate)).toRace(race);
        }
    }

    private BufferedReader getBufferedReader(Path path) throws IOException {
        InputStream is = Files.newInputStream(path);
        return new BufferedReader(new InputStreamReader(is));
    }

    private Stream<Path> getFilesByDate(Path root, LocalDate localDate) throws IOException {
        String formatterDate = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN_YYYYMMMDD).format(localDate);
        return Files.list(root).filter(filter -> filter.getFileName().toString().startsWith(formatterDate));
    }
}
