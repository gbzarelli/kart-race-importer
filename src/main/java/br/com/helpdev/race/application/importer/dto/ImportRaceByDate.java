package br.com.helpdev.race.application.importer.dto;

import java.time.LocalDate;

public class ImportRaceByDate {

    private final LocalDate localDate;

    public ImportRaceByDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "ImportRaceByDate{" +
                "localDate=" + localDate +
                '}';
    }

}
