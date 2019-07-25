package br.com.helpdev.race.domain.importer;

import br.com.helpdev.race.domain.race.Race;
import br.com.helpdev.race.shared.notification.Notifiable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Races extends Notifiable {

    public static Races getRaces(List<Race> races, LocalDate localDate) {
        return new Races(races, localDate);
    }

    private LocalDate localDate;
    private List<Race> items;

    private Races(List<Race> items, LocalDate localDate) {
        this.items = items;
        this.localDate = localDate;
    }

    public List<Race> getRaces() {
        return Collections.unmodifiableList(items);
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

}
