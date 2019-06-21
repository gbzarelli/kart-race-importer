package br.com.helpdev.race.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class RaceResources {

    public static final String KEY_LOG_RACES_FOLDER = "log.races.folder";
    private static Properties properties;

    public static final String APPLICATION_PROPERTIES = "application.properties";

    static {
        properties = new Properties();
        try {
            properties.load(
                    Objects.requireNonNull(
                            RaceResources.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Path getPathLogRacesFolder() {
        return Paths.get(properties.getProperty(KEY_LOG_RACES_FOLDER));
    }

}
