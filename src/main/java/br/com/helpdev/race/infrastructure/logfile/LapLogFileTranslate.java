package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.helpdev.race.shared.utils.TimeUtils.convertToLocalTime;

public class LapLogFileTranslate {

    public static LapEntity translate(String input) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String[] split = input.split("\\s+");

        String timeString = split[0].trim();
        int pilotNumber = Integer.parseInt(split[1].trim());
        int lapNumber = Integer.parseInt(split[split.length - 3].trim());
        String lapTimeString = split[split.length - 2].trim();

        LocalTime time = convertToLocalTime(
                timeString,
                pattern
        );
        String pilot = extractPilotName(input);
        LocalTime lapTime = convertToLocalTime(
                lapTimeString,
                pattern
        );
        float speedAvg = Float.parseFloat(split[split.length - 1].trim().replace(',', '.'));

        return new LapEntity(
                time,
                new PilotEntity(pilotNumber, pilot),
                lapNumber,
                lapTime,
                speedAvg
        );
    }

    private static String extractPilotName(String input) {
        Matcher matcher = Pattern.compile("[A-Z].*[A-Z]").matcher(input);
        if (matcher.find()) {
            return matcher.group();
        }
        return "<Not Found>";
    }


}
