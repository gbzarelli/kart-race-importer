package br.com.helpdev.race.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceResourcesTest {

    @Test
    @DisplayName("Should get path log races folder")
    void shouldGetPathLogRacesFolder() {
        final var pathLogRacesFolder = RaceResources.getPathLogRacesFolder();
        assertEquals("./races-test", pathLogRacesFolder.toString());
    }
}