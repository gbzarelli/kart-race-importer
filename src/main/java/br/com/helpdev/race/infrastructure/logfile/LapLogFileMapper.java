package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static br.com.helpdev.race.commons.time.TimeUtils.convertToLocalTime;

class LapLogFileMapper {

    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private LapLogFileMapper() {
    }

    static LapEntity parse(final String input) {
        final var split = input.split("\\s+");

        final var timeString = split[0].trim();
        final var pilotNumber = Integer.parseInt(split[1].trim());
        final var lapNumber = Integer.parseInt(split[split.length - 3].trim());
        final var lapTimeString = split[split.length - 2].trim();

        final var time = convertToLocalTime(
                timeString,
                pattern
        );
        final var pilot = extractPilotName(input);
        final var lapTime = convertToLocalTime(
                lapTimeString,
                pattern
        );
        final var speedAvg = Float.parseFloat(split[split.length - 1].trim().replace(',', '.'));

        return new LapEntity(
                time,
                new PilotEntity(pilotNumber, pilot),
                lapNumber,
                lapTime,
                speedAvg
        );
    }

    private static String extractPilotName(final String input) {
        final var matcher = Pattern.compile("[A-Z].*[A-Z]").matcher(input);
        if (matcher.find()) {
            return matcher.group();
        }
        return "<Not Found>";
    }


}
