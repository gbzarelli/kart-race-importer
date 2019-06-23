package br.com.helpdev.race.domain.importer;

import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.shared.notification.Notifiable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Races implements Notifiable {

    public static Races getRaces(List<Race> races, LocalDate localDate) {
        return new Races(races, localDate);
    }

    private LocalDate localDate;
    private List<Race> races;

    private Races(List<Race> races, LocalDate localDate) {
        this.races = races;
        this.localDate = localDate;
    }

    public List<Race> getRaces() {
        return Collections.unmodifiableList(races);
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

}
