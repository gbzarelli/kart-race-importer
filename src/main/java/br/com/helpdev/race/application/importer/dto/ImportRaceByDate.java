package br.com.helpdev.race.application.importer.dto;

import br.com.helpdev.race.shared.dto.InputDTO;

import java.time.LocalDate;

public class ImportRaceByDate extends InputDTO {

    private LocalDate localDate;

    public ImportRaceByDate(LocalDate localDate) {
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
