package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.importer.RaceRepository;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.domain.race.LapInfos;
import br.com.helpdev.race.domain.race.PilotRace;
import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RaceLogFileRepository implements RaceRepository {

    private static final String PATH_FILES = "./races/";

    @Override
    public Races importRaceByDate(LocalDate localDate) {
        List<Race> races = new ArrayList<>();
        try {
            Stream<Path> pathStream = getFilesByDate(localDate);
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
            LapEntity translate = LapTranslateLogFile.translate(line);
            PilotRace pilot = pilots.get(translate.getPilot().getNumber());
            if (pilot == null) {
                pilot = getPilotRaceFromLapEntity(race, translate);
                pilots.put(pilot.getNumber(), pilot);
            }
            pilot.newLap(getLapInfoFromLapEntity(translate));
        }
    }

    private LapInfos getLapInfoFromLapEntity(LapEntity translate) {
        return new LapInfos(translate.getTime(), translate.getLapTime(), translate.getSpeedAvg());
    }

    private PilotRace getPilotRaceFromLapEntity(Race race, LapEntity translate) {
        return new PilotRace(race, translate.getPilot().getNumber(), translate.getPilot().getName());
    }

    private BufferedReader getBufferedReader(Path path) throws IOException {
        InputStream is = Files.newInputStream(path);
        return new BufferedReader(new InputStreamReader(is));
    }

    private Stream<Path> getFilesByDate(LocalDate localDate) throws IOException {
        String formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(localDate);
        Path path = Paths.get(PATH_FILES);
        return Files.list(path).filter(filter -> filter.getFileName().toString().startsWith(formatterDate));
    }
}
