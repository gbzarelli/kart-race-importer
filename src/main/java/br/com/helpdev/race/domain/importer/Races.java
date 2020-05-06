package br.com.helpdev.race.domain.importer;

import br.com.helpdev.race.commons.objects.Notifiable;
import br.com.helpdev.race.domain.race.Race;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Races extends Notifiable {

    public static Races getRaces(final List<Race> races, final LocalDate localDate) {
        return new Races(races, localDate);
    }

    private final LocalDate localDate;
    private final List<Race> items;

    private Races(final List<Race> items, final LocalDate localDate) {
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
