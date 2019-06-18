package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import br.com.helpdev.race.infrastructure.logfile.entities.PilotEntity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LapTranslateLogFile {

    public static LapEntity translate(String input) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String[] split = input.split("\\s+");

        String timeString = split[0].trim();
        int pilotNumber = Integer.parseInt(split[1].trim());
        int lapNumber = Integer.parseInt(split[split.length - 3].trim());
        String lapTimeString = split[split.length - 2].trim();

        LocalTime time = convertToDateTimeFormat(
                timeString,
                pattern
        );
        String pilot = extractPilotName(input);
        LocalTime lapTime = convertToDateTimeFormat(
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

    private static LocalTime convertToDateTimeFormat(String timeString, DateTimeFormatter pattern) {
        String newTimeString = timeString;
        if (timeString.length() == 8) {
            newTimeString = "00:0" + timeString;
        } else if (timeString.length() == 9) {
            newTimeString = "00:" + timeString;
        } else if (timeString.length() == 10) {
            newTimeString = "0" + timeString;
        }
        return LocalTime.from(pattern.parse(newTimeString));
    }
}
