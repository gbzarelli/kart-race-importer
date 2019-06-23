package br.com.helpdev.race.application.importer.dto;

import br.com.helpdev.race.application.importer.dto.race.RaceDTO;
import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.shared.dto.OutputDTO;

import java.time.LocalDate;
import java.util.List;

public class RacesImported extends OutputDTO {

    private String date;
    private List<RaceDTO> races;

    public RacesImported(Status status, String date, List<RaceDTO> races) {
        super(status);
        this.date = date;
        this.races = races;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<RaceDTO> getRaces() {
        return races;
    }

    public void setRaces(List<RaceDTO> races) {
        this.races = races;
    }

    @Override
    public String toString() {
        return "RacesImported{" +
                "date='" + date + '\'' +
                ", races=" + races +
                '}';
    }
}
