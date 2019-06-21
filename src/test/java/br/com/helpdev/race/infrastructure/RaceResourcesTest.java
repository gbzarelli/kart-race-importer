package br.com.helpdev.race.infrastructure;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class RaceResourcesTest {

    @Test
    void getPathLogRacesFolder() {
        Path pathLogRacesFolder = RaceResources.getPathLogRacesFolder();
        assertEquals("./races",pathLogRacesFolder.toString());
    }
}