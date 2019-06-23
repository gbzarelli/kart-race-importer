package br.com.helpdev.race.application.importer.dto;

import br.com.helpdev.race.application.importer.dto.race.RaceDTO;
import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.shared.dto.OutputDTO;

import java.time.LocalDate;
import java.util.List;

public class RacesImported extends OutputDTO {

    private LocalDate localDate;
    private List<RaceDTO> races;

    public RacesImported(Status status, LocalDate localDate, List<RaceDTO> races) {
        super(status);
        this.localDate = localDate;
        this.races = races;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<RaceDTO> getRaces() {
        return races;
    }

    public void setRaces(List<RaceDTO> races) {
        this.races = races;
    }
}
