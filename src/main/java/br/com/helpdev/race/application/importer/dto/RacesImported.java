package br.com.helpdev.race.application.importer.dto;

import br.com.helpdev.race.application.importer.dto.race.RaceDTO;

import java.util.List;

public class RacesImported extends AbstractResponseDTO {

    private String date;
    private List<RaceDTO> races;

    public RacesImported(final Status status,
                         final String date,
                         final List<RaceDTO> races) {
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
