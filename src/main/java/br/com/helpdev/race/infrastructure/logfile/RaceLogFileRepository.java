package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.domain.importer.RaceRepository;
import br.com.helpdev.race.domain.importer.Races;
import br.com.helpdev.race.domain.race.PilotRace;
import br.com.helpdev.race.domain.race.Race;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

import static br.com.helpdev.race.infrastructure.RaceResources.getPathLogRacesFolder;
import static br.com.helpdev.race.infrastructure.logfile.RaceMapper.getLapInfoFromLapEntity;
import static br.com.helpdev.race.infrastructure.logfile.RaceMapper.getPilotRaceFromPilotEntity;

public class RaceLogFileRepository implements RaceRepository {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public Races importRacesByDate(final LocalDate localDate) {
        Objects.requireNonNull(localDate);

        final var races = new ArrayList<Race>();
        Races racesReturn = Races.getRaces(races, localDate);

        try {
            final var pathStream = getFilesByDate(getPathLogRacesFolder(), localDate);
            pathStream.parallel().forEach(path -> {
                try {
                    races.add(getRaceFromPath(path, localDate));
                } catch (IOException e) {
                    racesReturn.addNotification("Não foi possível carregar arquivo {" + path.getFileName() + "}");
                }
            });
        } catch (IOException e) {
            racesReturn.addNotification("Não foi possível carregar corridas, verifique o diretório de logs");
        }

        return racesReturn;
    }

    private Race getRaceFromPath(final Path path, final LocalDate date) throws IOException {
        final var bis = getBufferedReader(path);
        bis.readLine();//SKIP FIRST LINE
        final var race = new Race(path.toString(), date);
        fillRace(bis, race);
        return race;
    }

    private void fillRace(final BufferedReader bis, final Race race) throws IOException {
        final var pilots = new HashMap<Integer, PilotRace>();

        while (bis.ready()) {
            final var line = bis.readLine();
            final var entity = LapLogFileMapper.parse(line);
            var pilot = pilots.get(entity.getPilot().getNumber());
            if (pilot == null) {
                pilot = getPilotRaceFromPilotEntity(race, entity.getPilot());
                pilots.put(pilot.getPilotId().getNumber(), pilot);
            }
            pilot.newLap(getLapInfoFromLapEntity(entity)).toRace(race);
        }
    }

    private BufferedReader getBufferedReader(final Path path) throws IOException {
        final var is = Files.newInputStream(path);
        return new BufferedReader(new InputStreamReader(is));
    }

    private Stream<Path> getFilesByDate(final Path root, final LocalDate localDate) throws IOException {
        final var formattedDate = dateTimeFormat.format(localDate);
        return Files.list(root).filter(filter -> filter.getFileName().toString().startsWith(formattedDate));
    }
}
